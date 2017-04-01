//所有ajax请求添加csrf信息
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        }
    });
});