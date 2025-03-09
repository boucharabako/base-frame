/**
 * Include the token within all your Ajax requests. If you were using jQuery, this could be done with the following:
 * @returns
 */
$(function () {
    var MON_URL = location.protocol + '//' + location.host;
    var CSRF_TOKEN = $("meta[name='_csrf']").attr("content");
    var CSRF_HEADER = $("meta[name='_csrf_header']").attr("content");

    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
    });

    
});