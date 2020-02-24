let apiPrefix = "/api";
/*layui的模态框封装*/
function openModel(url,layer,title,fun,endFun)
{
    layer.open({
        title:title,
        type: 2,
        offset: 'auto',
        area: ['800px', '500px'],
        skin:'layui-layer-molv',
        fixed: false,
        shadeClose: false, //点击遮罩关闭
        content: url,
        success:fun,
        end:endFun
    });
}

function openMiniModel(url,layer,title,fun,endFun)
{
    layer.open({
        title:title,
        type: 2,
        offset: 'auto',
        area: ['500px', '400px'],
        skin:'layui-layer-molv',
        fixed: false,
        shadeClose: false, //点击遮罩关闭
        content: url,
        success:fun,
        end:endFun
    });
}


function openModelNoArea(url,layer,namastr)
{
    layer.open({
        type: 2,
        shadeClose: true,
        title:namastr,
        fixed: false, //不固定
        maxmin: true, //点击遮罩关闭
        content: url
    });
}
/*ajax封装*/
function ajaxPost($,url,data,fun)
{
    $.ajax({
        url: apiPrefix+url,
        data:data,
        type: 'post',
        contentType:"application/json",
        dataType: 'json',
        success: fun
    });
}
/*ajax封装*/
function ajaxDelete($,url,data,fun)
{
    $.ajax({
        url: apiPrefix+url,
        data:data,
        type: 'delete',
        contentType:"application/json",
        dataType: 'json',
        success: fun
    });
}

function ajaxGetBySync($,url,fun)
{
    $.ajax({
        url: apiPrefix+url,
        type: 'get',
        async:false,
        dataType: 'json',
        success: fun
    });
}
function ajaxGet($,url,fun)
{
    $.ajax({
        url: apiPrefix+url,
        type: 'get',
        dataType: 'json',
        success: fun
    });
}

function ajaxFuntionWithToken($,url,data,type,fun)
{
    $.ajax({
        url: url,
        data:data,
        type: type,
        contentType:"application/json",
        dataType: 'json',
        beforeSend:function(xhr)
        {
            xhr.setRequestHeader("Authentication",localStorage.getItem("token"));

        },
        success: fun
    });
}


function layuiSelect($,url,fun) {
    var htmls = '<option value="">请选择</option>'; //全局变量
    var resultData;
    ajaxGet($,url,function (result) {
        resultData = result.data;
       fun(htmls,resultData);
    })

}

