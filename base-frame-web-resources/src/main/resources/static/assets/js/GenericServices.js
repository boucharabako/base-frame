/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//var contextPath=$('#contextPathUrl').attr('href');
var notify = function (data) {
//    alert("1");
    var msgDetail = "";
    if (data.reasonPhrase) {
        msgDetail = "<div>" + data.reasonPhrase + "</div>";
    }
//    alert("2");
    if (data.errors) {
        msgDetail += "<ul>";
        for (var i = 0; i < data.errors.length; i++) {
            msgDetail = msgDetail + "<li> " + data.errors[i].message + "</li>";
        }
        msgDetail += "</ul>";
    }
//    alert("3");

    return msgDetail;
};


var showConfirmation = function (title, content, excecutorFunction) {
    $.confirm({
        title: 'Etes-vous sûr ' + title + ' ?',
        content: content,
//        content: 'Cet element sera définitivement supprimé',
        type: 'red',
        buttons: {
            ok: {
                text: "Valider",
                btnClass: 'btn-danger',
                keys: ['enter'],
                action: function () {
                    excecutorFunction();
                }
            },
            Abandonner: {
                text: "Abandonner",
                btnClass: 'btn-primary',
                action: function () {
                    console.log('the user clicked cancel');
                }
            }
        }
    });
};

var GenericService = {
    get: function (url) {


        var q = new Promise(function (resolve, reject) {
            $.ajax({

                url: contextPath + url,
                type: 'GET',
                success: function (dt_returned, textStatus, jqXHR) {
                    console.log("TATA 1");
                    console.log(dt_returned);
                    resolve(dt_returned);
                    hideSpinner();
                }, error: function (jqXHR, textStatus, errorThrown) {
                    console.log("Error " + errorThrown);
                    reject(jqXHR);

                }, beforeSend: function (xhr) {
//                    showSpinner();
//                    xhr.setRequestHeader(header, token);
                    console.log('Request started');
                }, complete: function (jqXHR, textStatus) {
                    console.log("Request complete");
//                      hideSpinner();
                }
            });

        });
        return q;
    }
    ,
    post: function (url, data) {
        var q = new Promise(function (resolve, reject) {
            $.ajax({
                url: contextPath + url,
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json',
                success: function (dt_returned, textStatus, jqXHR) {
                    console.log(jqXHR);
                    $.notify(jqXHR.getResponseHeader("X-nframe-alert"), {type: 'success'});
                    resolve(dt_returned);
                }, error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR.responseJSON);

                    if (jqXHR.responseJSON && jqXHR.responseJSON.reasonPhrase) {
                        $.notify(notify(jqXHR.responseJSON), {type: 'danger'});
                    }
                    reject(jqXHR.responseJSON);
                }, beforeSend: function (xhr) {
                    showSpinner();
//                    xhr.setRequestHeader(header, token);
                    console.log('Request started');
                }, complete: function (jqXHR, textStatus) {
                    hideSpinner();
                }
            });

        });
        return q;
    },
    delete: function (url, data) {
        var q = new Promise(function (resolve, reject) {
            $.ajax({
                url: contextPath + url,
                type: 'DELETE',
                data: data,
                success: function (dt_returned, textStatus, jqXHR) {

                    console.log(jqXHR.getResponseHeader("X-nframe-alert"));
                    $.notify(jqXHR.getResponseHeader("X-nframe-alert"), {type: 'success'});

                    resolve(dt_returned);
                }, error: function (jqXHR, textStatus, errorThrown) {

                    if (jqXHR.responseJSON && jqXHR.responseJSON.reasonPhrase) {
                        $.notify(notify(jqXHR.responseJSON), {type: 'danger'});
                    }
                    reject(jqXHR.responseJSON);
                }, beforeSend: function (xhr) {
//                    xhr.setRequestHeader(header, token);
                    showSpinner();
                    console.log('Request started');
                }, complete: function (jqXHR, textStatus) {
                    hideSpinner();
                }
            });

        });
        return q;
    },
    put: function (url, data) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: contextPath + url,
                type: 'PUT',
                data: data,
                success: function (dt_returned, textStatus, jqXHR) {

                    $.notify(jqXHR.getResponseHeader("X-nframe-alert"), {type: 'success'});
                    resolve(dt_returned);
                }, error: function (jqXHR, textStatus, errorThrown) {

                    if (jqXHR.responseJSON && jqXHR.responseJSON.reasonPhrase) {
                        $.notify(notify(jqXHR.responseJSON), {type: 'danger'});
                    }

                    reject(jqXHR.responseJSON);
                }, beforeSend: function (xhr) {
//                    xhr.setRequestHeader(header, token);
                    showSpinner();
                    console.log('Request started');
                }, complete: function (jqXHR, textStatus) {
                    hideSpinner();
                }
            });

        });
    }
};