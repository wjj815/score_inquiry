layui.define(["jquery"],function(exports) {
    "use strict";

    var $ = layui.jquery,
        Class = function () {
        };

    Class.prototype.render = function(options){
        var othis = this;
        othis.id = options.id;
        var dataTableDOM = $(options.id).next().find('div.layui-table-body table')[0];
        var tdDOM = $(dataTableDOM).find("td[data-field='"+options.field+"']");
        if(!options.data){
            $.getJSON(options.url,options.where,function (result) {
                if(options.parseData){
                    othis.data = options.parseData(result.data);
                }else {
                    othis.data = result.data;
                }
                tdDOM.each(function () {
                    var text = $(this).find("div.layui-table-cell").eq(0).text();
                    text = othis.getOption(text);
                    $(this).find("div.layui-table-cell").eq(0).text(text);
                });
            });
        }else {
            othis.data = options.data;
            tdDOM.each(function () {
                var text = $(this).find("div.layui-table-cell").eq(0).text();
                text = othis.getOption(text);
                $(this).find("div.layui-table-cell").eq(0).text(text);
            });
        }
        othis.callback = options.callback;

        //解决下拉框超出表格最大高度时，被覆盖的问题。
        $(options.id).next().find("div.layui-table-body,div.layui-table-box,div.layui-table-header").css('overflow', 'visible');
        tdDOM.bind('click',function (e) {
            var that = this;
            if(!othis.deleteAll(that)){
                return;
            }
            var input = $('<input class="layui-input layui-table-edit layui-table-select-input" placeholder="关键字搜索">');
            var icon = $('<i class="layui-icon layui-table-select-edge" data-td-text="'+$(that).find("div.layui-table-cell").eq(0).text()+'" >&#xe625;</i>');
            $(that).append(input);
            $(that).append(icon);
            input.focus();
            input.bind('input propertychange', function(){
                var val = this.value;
                var dl = $(that).find('dl').eq(0);
                var searchDDs = [];
                if(val === null || val === '' || val.length === 0){
                    searchDDs = othis.createHtml(othis.data);
                }else {
                    searchDDs = othis.searchHtml(othis.data,val);
                }
                dl.html("");
                dl.prepend(searchDDs.join(" "));
                othis.ddClick(that);
            });
            icon.bind('click',function () {
                layui.stope();
                var thisTd = $(this.parentNode);
                othis.deleteSelect(thisTd);
            });
            //layui.stope(input);
            var divClass;
            var thisY = othis.getElementY(that)+that.offsetHeight;
            var winHeight = $(window).height();
            //当前y坐标大于窗口0.55倍的高度则往上延伸，否则往下延伸。
            if(thisY > 0.55*winHeight){
                //往上延伸
                divClass = othis.getBrowser() === 'chrome'?"layui-table-select-div-chrome-up":"layui-table-select-div-ie-up";
            }else {
                //往下延伸
                divClass = othis.getBrowser() === 'chrome'?"layui-table-select-div-chrome-down":"layui-table-select-div-ie-down";
            }
            var divHtml = $('<div class="layui-table-select-div '+divClass+'"></div>');
            var selectHtml = [];
            selectHtml.push('<dl>');
            othis.createHtml(othis.data,selectHtml);
            selectHtml.push('</dl>');
            divHtml.append(selectHtml.join(" "));
            $(that).append(divHtml);
            othis.ddClick(that);
            $(that).mouseleave(function () {
                othis.deleteSelect(that);
            });
        });
    };

    //获取元素y坐标
    Class.prototype.getElementY = function(el){
        return el.offsetParent ? el.offsetTop + this.getElementY(el.offsetParent) : el.offsetTop;
    };

    //给下拉列表注册点击事件
    Class.prototype.ddClick = function(that){
        var othis = this;
        $(that).find('dl dd').bind('click',function (e) {
            layui.stope(e);
            var name = $(this).attr('lay-value');
            othis.deleteSelect(that);
            if(othis.callback){
                var update = {name:name,value:$(this).text()};
                $(that).find("div.layui-table-cell").eq(0).text(update.value);
                othis.callback(update);
            }
        });
    };

    //获得的浏览器
    Class.prototype.getBrowser = function(){
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        if (userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Edge") <= -1){
            return "chrome";
        }
        if (userAgent.indexOf('NET') > -1 && userAgent.indexOf("rv") > -1) {
            return "ie";
        }
        if(userAgent.indexOf('Edge') > -1){
            return "edge";
        }
        return  "chrome";
    };

    //生成下拉选择框的html代码
    Class.prototype.createHtml = function(selectData,data){
        if(!data)data = [];
        selectData.forEach(function (e) {
            data.push('<dd lay-value="'+e.name+'" class="layui-table-select-dd">'+e.value+'</dd>');
        });
        return data;
    };
    //生成根据关键字搜索的下拉选择框
    Class.prototype.searchHtml = function(selectData,search,data){
        if(!data)data = [];
        selectData.forEach(function (e) {
            if((e.value+'').indexOf(search)>-1){
                data.push('<dd lay-value="'+e.name+'" class="layui-table-select-dd">'+e.value+'</dd>');
            }
        });
        return data;
    };

    //获取一个下拉框的数据
    Class.prototype.getOption = function(value){
        var othis = this;
        var dataArr = othis.data;
        if(!dataArr)return '';
        for(var i=0;i<dataArr.length;i++){
            var e = dataArr[i];
            if((e.name+'') === (value+'')){
                return e.value;
            }
        }
        return '';
    };

    //删除所有临时生成的div和input
    Class.prototype.deleteAll = function(td){
        var othis = this;
        var passStat = true;
        var tableDOM = $(othis.id).next().find('div.layui-table-body table');
        $(tableDOM).find('td').each(function () {
            var input = $(this).find('input.layui-table-select-input');
            if(td === this && input.length > 0){
                passStat = false;
                return;
            }
            othis.deleteSelect(this);
        });
        return passStat;
    };

    //删除当前临时生成的div和input
    Class.prototype.deleteSelect = function(that){
        $(that).find('div.layui-table-select-div').remove();
        $(that).find('input.layui-table-select-input').blur();
        $(that).find('input.layui-table-select-input').remove();
        var icon = $(that).find('i.layui-table-select-edge').eq(0);
        var text = icon.attr('data-td-text');
        $(that).find("div.layui-table-cell").eq(0).text(text);
        icon.remove();
    };

    var active = {
        render:function (options) {
            new Class().render(options);
        }
    };
    layui.link(layui.cache.base + 'css/layuiTableColumnSelect.css');
    exports('layuiTableColumnSelect', active);
});