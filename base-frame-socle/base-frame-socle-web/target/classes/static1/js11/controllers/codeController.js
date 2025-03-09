/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Assima
 */
//'use strict';
var App;
var appUrl;
App.controller('codeController', ['$scope', '$rootScope'
            , function ($scope, $rootScope) {
             $scope.zero = true;
             jslog("New Personne : " + angular.toJson($scope.zero));
             
      $scope.changerXStatut = function(){
        
          $scope.zero = true;
                 jslog("New Personne changer : " + angular.toJson($scope.zero));
      };


/////

   var stat;
            var porteDomaine = "D";
            var global_domaine;
            var global_statut;
            var global_type;
            var initTree = true;
//            $(window).bind("load", function () {
//                var domaineTree = $('#basicTree').jstree("get_selected");
//                var porteTree = $('#dragTree').jstree("get_selected");
//
//                if (domaineTree.length !== 0) {
////                    alert('domaineTree = ' + domaineTree);
//                    pel = domaineTree[0];
//                    t = "D";
//                    var info = checkStatus(pel);
//                    $("#type_liste").html("Registre des " + $("#" + pel).attr("data-type") + " codifiés (" + info + ")");
//                }
//                if (porteTree.length !== 0) {
////                    alert('porteTree = ' + porteTree);
//                    pel = porteTree[0];
//                    $("#type_liste").html("Registre des " + $("#" + pel).attr("data-type") + " codifiés");
//                }
//            });

            var pel;
            var t;

//            $('#basicTree').bind("init.jstree", function (evt, data) {
//                var domaineTree = $('#basicTree').jstree("get_selected");
////                alert('domaineTree 2 = ' + domaineTree);
//                if (domaineTree.length !== 0) {
//                    pel = domaineTree[0];
//                    t = "D";
//                    var info = checkStatus(pel);
//                    $("#type_liste").html("Registre des " + $("#" + pel).attr("data-type") + " codifiés (" + info + ")");
//                    loadTree();
//                }
//            });
//
//            $('#dragTree').bind("loaded.jstree", function (evt, data) {
//                var porteTree = $('#dragTree').jstree("get_selected");
////                alert('porteTree 2 = ' + porteTree);
////                alert(JSON.stringify(porteTree));
//
//                if (porteTree.length !== 0) {
//                    pel = porteTree[0];
//                    $("#type_liste").html("Registre des " + $("#" + pel).attr("data-type") + " codifiés");
//                    loadTree();
//                }
//            });

//            });

            loadTree = function (data) {
              
                
                pel = data.node.id;
                global_domaine = $("#" + pel).attr("data-domaine");
                global_statut = $("#" + pel).attr("data-statut");
                global_type = $("#" + pel).attr("data-type");
                if (global_type) {
                       jslog("---------------");
                $scope.zero = true;
                     jslog("--------" + $scope.zero);
                    $("#btn_add_row").attr("data-domaine", global_domaine);
                    $("#btn_add_row").attr("data-statut", global_statut);
                    $("#btn_add_row").attr("data-type", global_type[0].toUpperCase());
                    $("#type_liste").html("Registre des " + $("#" + pel).attr("data-type") + " codifiés");
                    var url = appUrl + "parametre/configuration_de_base/detail/" + t + "/" + global_type[0].toUpperCase() + "/" + $("#" + pel).attr("data-id") + "/" + $("#" + pel).attr("data-domaine");
                    doGlobalGetWithCallbacks(url, "codification_tble", function () {
                        loadStatus();
                    }, function () {
                    });
                }
            };

            checkStatus = function (el) {

                var id = $("#" + el).attr('data-id');
                var info = "";
                if (id === "1") {
                    info = "En saisie";
                    $("#btn_add_row").attr("style", "display:block");
                }
                if (id === "2") {
                    info = "Actif";
                    $("#btn_add_row").attr("style", "display:none");
                }
                if (id === "3") {
                    info = "Obsolète";
                    $("#btn_add_row").attr("style", "display:none");
                }
                return info;
            };



            $(document).ready(function ($) {
//                 $('#basicTree').jstree("refresh");
//                 $('#dragTree').jstree("refresh");
                $('#basicTree').on("select_node.jstree", function (evt, data) {
                    if (initTree === true) {
                        $('#' + data.node.id).click();
                        $('#dragTree').jstree("deselect_all");
                        initTree = false;
                    }
                });

                $('#dragTree').on("select_node.jstree", function (evt, data) {
                    if (initTree === true) {
                        $('#' + data.node.id).click();
                        $('#basicTree').jstree("deselect_all");
                        initTree = false;
                    }
                });


                loadStatus();


                $("#fm").parsley().on('field:validated', function () {
                });
                var st = $('#ultimate_statut').attr("data-statut") + "";
                if (st === "1") {
                    $("#btn_add_row").attr("style", "display:block");
                } else {
                    $("#btn_add_row").attr("style", "display:none");
                }
                init();

                $('.dte_pick').datepicker({
                    autoclose: true,
                    todayHighlight: true,
                    format: 'dd/mm/yyyy',
                    startDate: 'd'
                });
                $('.sa-delee').click(function () {
                    var idClicked = $(this).closest("tr").attr("id");
                    var location = $(this).attr('href');
                    var deletable = $(this).attr('disabled');
                    if (!deletable) {
                        showConfirmation('supprimer cet élément', function () {
                            $.ajax({
                                url: location,
                                type: 'GET',
                                success: function (data) {
                                    $('#' + idClicked).remove();
                                }, error: function (data) {

                                }
                            });
                        });
                    }
                });
            }
            );

            $('#btn').click(
                    function () {
                        loadNode();
                    });


           loadNode =  function () {
                var domaineTree = $('#basicTree').jstree("get_selected");
                var porteTree = $('#dragTree').jstree("get_selected");
            };

            init = function () {

                $('#tbleau').dataTable({
                    pageLength: 5,
                    searching: true,
                    scrollY: 400,
                    scrollX: 1100,
                    scrollCollapse: true,
                    paging: true,
                    "ordering": false,
                    "info": false,
                    language: {
                        processing: "Traitement en cours...",
                        search: "Rechercher&nbsp;:",
                        lengthMenu: "Afficher _MENU_ &eacute;l&eacute;ments",
                        info: "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ment(s)",
                        infoEmpty: "Affichage de l'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ment",
                        infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                        infoPostFix: "",
                        loadingRecords: "Chargement en cours...",
                        zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
                        emptyTable: "Aucune donnée trouvée",
                        paginate: {
                            first: "Premier",
                            previous: "Pr&eacute;c&eacute;dent",
                            next: "Suivant",
                            last: "Dernier"
                        },
                        aria: {
                            sortAscending: ": activer pour trier la colonne par ordre croissant",
                            sortDescending: ": activer pour trier la colonne par ordre décroissant"
                        }
                    }
                });
            };

            openDetail = function(event) {
                    jslog("---------------");
                $scope.zero = true;
                     jslog("--------" + $scope.zero);
                var url = $(event).attr('href');
                var valeur = $(event).attr('data-type');
                var id = $(event).attr('data-id');
                var domaine = $(event).attr('data-domaine');
                stat = id;
                var info = "";
                if (id === "1") {
                    info = "En saisie";
                    $("#btn_add_row").attr("style", "display:block");
                } else if (id === "2") {
                    info = "Actif";
                    $("#btn_add_row").attr("style", "display:none");
                } else if (id === "3") {
                    info = "Obsolète";
                    $("#btn_add_row").attr("style", "display:none");
                } else {
                    $("#btn_add_row").attr("style", "display:none");
                } 

                $("#type_liste").html("Registre des " + valeur + " codifiés (" + info + ")");
                $("#btn_add_row").attr("data-statut", id);
                $("#btn_add_row").attr("data-type", $(event).attr('data-type').toString()[0].toUpperCase());
                $("#btn_add_row").attr("data-domaine", $(event).attr('data-domaine'));
                if (id === '1') {
                    $("#btn_add_row").attr("style", "display:block");
                } else {
                    $("#btn_add_row").attr("style", "display:none");
                }
                doGlobalGetWithCallbacks(url, "codification_tble", function () {
                    loadStatus();
                }, function () {
                });

            };

            clickOnDomaine = function () {
                porteDomaine = "D";
            };

           clickOnPorte = function () {
                porteDomaine = "P";
            };

           addRow = function (event) {
                //var statut = $($(".span_type")[0]).attr('data-statut');
                var type = $("#btn_add_row").attr('data-type');
                var statut = $("#btn_add_row").attr('data-statut');
                var domaine = $("#btn_add_row").attr('data-domaine');
                var libelle = $(event).siblings(".libelle").val();
                var porte = $(event).siblings(".porte").val();
                var url;
                if (type === 'L') {
                    url = appUrl + "parametre/conf/listes/add/new/" + porteDomaine + "/" + type + "/" + statut + "/" + domaine;//"?libelle=" + libelle + "&amp;porte=" + porte;
                } else if (type === 'P') {
                    url = appUrl + "parametre/conf/parameter/" + porteDomaine + "/" + domaine;
                } else {
                    url = appUrl + "parametre/conf/etiquette/" + porteDomaine + "/" + domaine;
                }
                window.location = url;

            };

           deleteCodification = function (el) {
                var code = $(el).attr("data-code");
                var url = appUrl + 'parametre/configuration_de_base/delete/' + code;
                showConfirmation('supprimer cet élément ', function () {
                    doGlobalGet(url, "codification_tble", function () {
                        $('#cod_' + code).remove();
                        initTree();
                    }, function () {
                        initTree();
                    });
                });
            };

           saveCodif = function() {
                var codificationDTO = {
                    code: $("#code").val(),
                    libelle: $("#libelle").val(),
                    domain: $("#domaine").val(),
                    porte: $("#porte").val(),
                    modify: $("#modify").val(),
                    dateDebutValidite: $("#dateDebut").val(),
                    dateFinValidite: $("#dateFin").val(),
                    dateActivation: $("#dateActivation").val(),
                    version: $("#version").val()
                };
                $.ajax({
                    url: "saveCodification?domaine=" + $("#domaine").val() + "&amp;porte=" + $("#porte").val() + "&amp;modify=" + $("#modify").val(),
                    type: 'POST',
                    data: codificationDTO,
                    success: function (data, textStatus, jqXHR) {
                        success(); //("succes " + JSON.stringify(codifList));

                    }, error: function (jqXHR, textStatus, errorThrown) {
                        alert('Error');
                        error();
                    }
                });

            };
}]);

