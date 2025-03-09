/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global angular, Json, logger, firstPage, listProfilURL, firstpage */

'use strict';
var App;
var appUrl;
App.controller("profileController", ['$scope', 'GenericService', function ($scope, GenericService, $notify, spModal) {
        const urlBase = appUrl + "api/gestion/profil";
        const getValeurIdProfilURL = urlBase + "/valeurIdProfil";
        const getIdprofilsURL = urlBase + "/idProfils";
        const getIdProfilURL = urlBase + "/idProfil";
        const saveOrUpdateProfilURL = urlBase + "/save";
        const detailProfilURL = urlBase + "/getProfil";
        const deleteProfilURL = urlBase + "/delete";
        const editProfilURL = urlBase + "/editProfil";
        const editHabilitationFonct = urlBase + "/upDateHabilitationFonc";
        const changerEtatURL = urlBase + "/changerEtat";
        const listeFonctionnaliteURL = urlBase + "/listFonction";
        const listeHabilitationURL = appUrl + "api/sec/parametre/fonction";
        const saveHabiliFoncURL = urlBase + "/saveHabilitation";
        const listeProfilURL = urlBase + "/list";
        const listeProfilURLs = urlBase + "/listeProfils";
        const listeHabiliFonct = urlBase + "/listeHabiliteFonctionnelle";
        const deleteHabiliFonctURL = urlBase + "/deleteHabilitation";
        const detailHabiliFonctURL = urlBase + "/detailHabiliFonct";
        const listeHabiliFonctionnelleURL = urlBase + "/listHabilitation";
        const listeNiveauHabilitationURL = urlBase + "/listNiveauHabilitation";

        var c = this;

        /*$scope.list = true;*/
        $scope.pagi = true;
        $scope.disab = false;
        $scope.searchObject = {profil: ''};
        $scope.objetFonctionnalite = {id: null, code: null};
        $scope.objetProfils = {id: null, code: null};
        $scope.objetHabilitation = {id: null, code: null};
        $scope.objetHabilitationFonctionnelle = {id: null, idProfil: null, codeFonction: null, libelleFonction: null, idNiveauHabilitation: null, libelleNiveauHabilitation: null, disabled: false};
        $scope.idProfil = {id: null};
        $scope.objetHabiFoncMaster = {id: null, idProfil: null, libelleFonction: null, codeFonction: null, habilitation: null, libelleHabilitation: null};
        $scope.objetProfil = {id: null, code: null, intitule: null, description: null, etat: null, libelleEtat: null, workFlowCycleEtatSimpleObject: null,
            actionObject: null, listActions: [], disabled: false, canEdit: false};
        $scope.objetMaster = {id: null, code: null, intitule: null, statut: null,
            isActif: false, isObsolete: false, isEnSaisie: true, concepteMetier: null, classification: []};
        $scope.titleModale;
        // variable de la pagination
        $scope.listeObjetProfil = [];
        $scope.listeObjetFonctionnalite = [];
        $scope.listeNiveaux = [];
        $scope.listeObjetProfils = [];
        $scope.listeObjetHabilitations = [];
        $scope.listeObjetHabilitationFonc = [];
        $scope.listeObjetMaster = [];
        $scope.listeVide = [];
        $scope.actionProfil = null;
        $scope.listeObjetHabilitationFonctionnelle = [];
        // pagination profil
        var firstPage = 1;
        $scope.initialNumerOfElements = 5;
        $scope.pageSizeSelect = 5;
        $scope.pageSizeSelectH = 5;
        $scope.memoryPage = firstPage;
        $scope.pageSize = [];
        $scope.sequence = [];
        $scope.selectedPageSize = null;
        $scope.paginationObject = {selectedPageSize: ""};
        $scope.pager = {startPage: "", endPage: "", totalPages: "", currentPage: "", buttonsToShow: ""};
        $scope.listPage = {number: ""};
        // pagination habilitation fonctionnelle
        var firstPages = 1;
        $scope.initialNumerOfElementss = 10;
        $scope.pageSizeSelects = 10;
        $scope.memoryPages = firstPages;
        $scope.pageSizes = [];
        $scope.sequences = [];
        $scope.selectedPageSizes = null;
        $scope.paginationObjects = {selectedPageSizes: ""};
        $scope.listePages = {number: ""};
        //
        $scope.disabDelete = false;
        $scope.disabledCode = false;
        $scope.disabledLibelle = false;
        $scope.dsblBtn = false;
        $scope.displaySaveButton = true;
        $scope.displayCancelButton = true;
        $scope.displayExitButton = false;
        $scope.displayWkf = false;
        $scope.saveShow = true;
        $scope.closeShow = true;
        $scope.modalClose = true;
        $scope.ligne = false;
        $scope.val = false;
        //
        $scope.deleteShow = true;
        $scope.editShow = true;
        $scope.detailShow = true;
        $scope.showActive = true;
        $scope.etatCombo = true;
        $scope.tableau = true;
        $scope.tableHabilitation = true;
        $scope.sta = true;
        $scope.titre = true;

        $scope.selectedProfil = null;
        $scope.searchMotCle = null;
        // $scope.searchObject = {mc: '', profil: ''};
        //  $scope.searchObjectMaster = {mc: '', profil: ''};
        $scope.searchObject = {mc: ''};//, profil: ''};
        $scope.searchObjectMaster = {mc: ''};//, profil: ''};

        $scope.addNew = function () {
            $scope.titleModale = "Ajout d'un profil";
            //  $scope.pagi = false;
            $scope.disabledCode = false;
            $scope.disabledLibelle = false;
            $scope.saveShow = true;
            $scope.closeShow = true;
            $scope.modalClose = false;
            $scope.listeObjetProfil = [];
            $scope.objetMaster.concepteMetier = "En Saisie";
            $scope.memoryPage = firstPage;
            $scope.objetProfil.id = null;
            $scope.objetProfil.code = null;
            $scope.objetProfil.intitule = null;
            $scope.displaySaveButton = true;
            $scope.displayCancelButton = true;
            $scope.displayExitButton = false;
            $scope.displayWkf = false;
            $scope.objetProfil.isActif = false;
            $scope.listeDesProfil();
            $scope.objetHabilitationFonctionnelle.codeFonction = null;
            $scope.objetHabilitationFonctionnelle.idNiveauHabilitation = null;
            $scope.listeHabilitationFonctionnelles(null);
            $scope.tableau = false;
            $scope.tableHabilitation = false;
            $scope.sta = false;
            $scope.titre = false;
            $scope.statutDto = true;
            $scope.objetProfil.statutDto.libelle = "En Saisie";
            $scope.pagination = "Ajout d'un profil";
            $scope.listeObjetHabilitationFonctionnelle = [];
            // $scope.list = false;
            $scope.ligne = false;

        };
        $scope.afficherActiveButton = function () {
            if ($scope.objetProfil.statutDto.libelle === "Obsolete") {
                $scope.showActive = false;
            }
        };
        $scope.saveProfil = function (objetProfil) {
            // alert(objetProfil);
            $scope.modDetail = false;
            $scope.test = false;
            $scope.statutDto = true;
            $scope.objetMaster.code = $scope.objetProfil.code;
            $scope.objetMaster.intitule = $scope.objetProfil.intitule;
            $scope.objetMaster.statut = $scope.objetProfil.statut;

            GenericService.post(saveOrUpdateProfilURL, angular.toJson($scope.objetProfil))
                    .then(
                            function (data) {
                                if (data.dto) {
                                    $scope.objetProfil = angular.copy(data.dto);
                                    $scope.listeDesProfil();
                                    $scope.tableau = true;
                                    $scope.tableHabilitation = true;
                                    $scope.objetProfil.id = $scope.getIdProfil($scope.objetProfil.code);
                                    //  $scope.detailProfil($scope.objetProfil.id);
                                    $scope.editerProfil($scope.objetProfil.id);
                                    $scope.listeHabilitationFonctionnelles($scope.objetProfil.id);
                                    $scope.displayWkf = true;
                                    $scope.vide();
                                    $scope.listeDesFonctionnalite();
                                    $scope.videHabilitation();
                                    if ($scope.objetProfil.statutDto.libelle === "Obsolete") {
                                        $scope.sta = false;
                                        $scope.titre = false;
                                    }
                                }
                                $scope.objetProfil = {id: null, code: null, intitule: null, description: null, etat: null, libelleEtat: null, workFlowCycleEtatSimpleObject: null,
                                    actionObject: null, listActions: [], disabled: false, canEdit: false};
                                //$scope.listeHabilitationFonctionnelles($scope.objetProfil.id);
                                 $('#detail-profil').modal('hide');
                            },
                            function () {
                            }
                    );

        };

        $scope.listeDesProfil = function () {

//            $scope.paginate($scope.initialNumerOfElements, firstPage);
//            $scope.changePageSize();
//            $scope.searchObject = angular.copy($scope.searchObjectMaster);
//            $scope.searchObject = angular.copy($scope.searchObjectMaster);
//            if ($scope.searchMotCle !== null) {
//                $scope.searchObject.mc = $scope.searchMotCle;
//            }
            $scope.totalElements = 0;
            $scope.totalElementsH = 0;
            //$scope.profil;
            var url = listeProfilURL + "/" + $scope.initialNumerOfElements + "/" + firstPage;
            GenericService.get(url + "/?mc=" + $scope.searchObject.mc)
                    .then(
                            function (data) {
                                $scope.listeObjetProfil = data.listProfil.content;
                                $scope.listPage = data.listProfil;
                                jslog("aaaaaaaaaaaaaaaaaaaaaa:" + angular.toJson($scope.listPage));
                                $scope.pageSizes = data.pageSize;
                                $scope.pageSizeSelect = $scope.pageSizes[0];
                                $scope.sequence = data.sequence;
                                $scope.totalElements = data.listProfil.totalElements;
                                jslog("LISTE EMPLACEMENT = " + angular.toJson(data.totalElements));
                            },
                            function () {}

                    );
        };
        $scope.listeDesProfil();

        //$scope.listeDesProfil();

        $scope.listeHabilitationFonctionnelles = function (idProfil) {
            GenericService.get(listeHabiliFonctionnelleURL + "/" + $scope.initialNumerOfElementss + "/" + firstPages + "/" + idProfil)
                    .then(
                            function (data) {
                                $scope.listeObjetHabilitationFonctionnelle = data.listHabilitation.content;
                                $scope.listPageH = data.listHabilitation;
                                jslog("bbbbbbbbbbbbb:" + angular.toJson($scope.listPageH));
                                $scope.pageSizesH = data.pageSize;
                                $scope.pageSizeSelectsH = $scope.pageSize[0];
                                $scope.sequencesH = data.sequence;
                                $scope.totalElementsH = data.listHabilitation.totalElements;
                                //$scope.changePageHabiliSize();
                            },
                            function () {}
                    );
        };


        $scope.listeDesFonctionnalite = function () {
            var url = listeFonctionnaliteURL;
            GenericService.get(url)
                    .then(
                            function (data) {
                                $scope.listeObjetFonctionnalite = data.listFonction;

                            },
                            function () {}
                    );

        };
        $scope.listeDesFonctionnalite();

        $scope.listeDesHabilitation = function () {
            var url = listeNiveauHabilitationURL;
            GenericService.get(url)
                    .then(
                            function (data) {
                                $scope.listeNiveaux = data.listNiveau;

                            },
                            function () {}
                    );

        };
        $scope.listeDesHabilitation();

        $scope.listeDesHabilations = function (id) {
            var url = listeHabilitationURL;
            GenericService.get(url + "/" + id)
                    .then(
                            function (data) {
                                $scope.listeObjetHabilitations = data;

                            }
                    );
        };
        $scope.getIdProfil = function (code) {
            var url = getIdProfilURL;
            GenericService.get(url + "/" + code)
                    .then(
                            function (data) {
                                $scope.idProfil = data;
                                $scope.objetProfil.id = $scope.idProfil.id.toString();
                            }
                    );
        };
        $scope.getValeurIdProfil = function (code) {
            var url = getValeurIdProfilURL;
            GenericService.get(url + "/" + code)
                    .then(
                            function (data) {
                                $scope.valeurProfil = data;
//                                alert(angular.toJson($scope.valeurProfil));

                            });
        };

        $scope.getIdprofils = function (code) {
            var url = getIdprofilsURL;
            GenericService.get(url + "/" + code)
                    .then(
                            function (data) {
                                $scope.listeObjetProfils = data.idProfils;
//                                alert(angular.toJson($scope.listeObjetProfils));
                                //   jslog("Liste des id :" + angular.toJson(data.idProfils));

                            }
                    );
        };


        $scope.afficheButtonSave = false;
        $scope.editer = function (index) {
            $scope.listeObjetHabilitationFonctionnelle[index].disabled = false;
            alert("1");
        };

        $scope.nonEditer = function (index) {
            $scope.listeObjetHabilitationFonctionnelle[index].disabled = false;
        };
        $scope.listeHabiByFonction = function (codeFonction, index) {

            $scope.listeObjetHabilitationFonctionnelle[index].edit = '1';
            $scope.liste = [];

            var url = listeHabilitationURL;
            GenericService.get(appUrl + "api/sec/parametre/fonction/" + codeFonction)
                    .then(
                            function (data) {
                                if (data) {

                                    $scope.listeObjetHabilitationFonc = data;
                                    $scope.listeObjetMaster[index] = data;
                                    $scope.objetHabiFoncMaster = data;
                                    $scope.listeObjetHabilitationFonctionnelle[index].listeHabilitation = data;
                                    $scope.listeObjetHabilitationFonctionnelle[index].disabled = true;

                                    //   jslog("Liste  :" + angular.toJson(data.habilitations));
                                }
                            },
                            function () {}
                    );

        };



        $scope.detailProfil = function (id) {
            $scope.titleModale = "Détail d'un profil";


            //  $scope.pagi =true;
            $scope.saveShow = false;
            $scope.closeShow = false;
            $scope.modalClose = true;
            $scope.disabledCode = true;
            $scope.disabledLibelle = true;
            $scope.displaySaveButton = false;
            $scope.displayCancelButton = false;
            $scope.displayExitButton = true;
            $scope.vide();
            $scope.disab = true;
            $scope.disabDelete = true;
            $scope.listeDesFonctionnalite();
            $scope.listeDesHabilations(null);
            if (id !== null && id !== undefined) {
                GenericService.get(detailProfilURL + "/" + id)

                        .then(
                                function (data) {
                                    if (data) {
                                        $scope.objetProfil = data.detailProfil;
                                        $scope.objetHabilitationFonctionnelle.idProfil = $scope.objetProfil.id;
                                        $('#idProfile').val($scope.objetProfil.id);
                                        $scope.listeHabilitationFonctionnelles($scope.objetProfil.id);
                                        $scope.listeDesFonctionnalite();
                                        $scope.listeDesHabilations(null);
                                        $scope.displayWkf = true;
                                        $scope.ligne = false;
                                    }
                                    if ($scope.objetProfil.statutDto.libelle === "Obsolete") {
                                        $scope.objetHabilitationFonctionnelle.idProfil = null;
                                        $scope.sta = false;
                                        $scope.titre = true;
                                        $scope.tableau = false;
                                        $scope.tableHabilitation = false;
                                        $scope.ligne = false;
                                        $scope.list = false;
                                    } else if ($scope.objetProfil.statutDto.libelle === "Actif") {
                                        $scope.sta = true;
                                        $scope.titre = true;
                                        $scope.tableau = true;
                                        $scope.tableHabilitation = true;
                                        $scope.ligne = true;
                                        $scope.list = true;
                                    }
                                    if ($scope.objetProfil.statutDto.libelle === "En saisie") {
                                        $scope.sta = true;
                                        $scope.titre = true;
                                        $scope.tableau = true;
                                        $scope.tableHabilitation = true;
                                        $scope.ligne = true;
                                        $scope.list = true;
                                    } else {

                                    }
                                    if ($scope.objetProfil.statutDto.libelle === '') {
                                        $scope.sta = true;
                                        $scope.titre = true;
                                        $scope.tableau = true;
                                        $scope.tableHabilitation = true;
                                    }

                                    if ($scope.objetProfil.statutDto.libelle === "Actif") {
                                        $scope.sta = true;
                                        $scope.titre = true;
                                    }
                                    if ($scope.objetProfil.statutDto.libelle === "En saisie") {
                                        $scope.sta = true;
                                        $scope.titre = true;
                                    }

                                    /* if($scope.objetProfil.flgProfil === "4"){
                                     $scope.check();
                                     }
                                     if($scope.objetProfil.flgProfil ==="5" || $scope.objetProfil.flgProfil === null || $scope.objetProfil.flgProfil == undefined){
                                     
                                     }*/
                                    $scope.check($scope.objetProfil.flgProfil);
                                    jslog("liste des profils : " + angular.toJson($scope.objetProfil));
                                },
                                function () {
                                }

                        );
                $scope.listeHabilitationFonctionnelles($scope.objetHabilitationFonctionnelle.idProfil);
                $scope.listeDesFonctionnalite();
                $scope.listeDesHabilations(null);
            }


        };


        $scope.deleteProfil = function (id) {
            
            var dialog = $('#myModal').modal('show');
            $('#btnYes').click(function () {
                GenericService.delete(deleteProfilURL + "/" + id)
                        .then(
                                function (data) {
                                    $('#myModal').modal('hide');
                                    $scope.listeDesProfil();
                                    
                                },
                                function () {
                                }
                        );
            });
            $('#btnNo').click(function () {
                //dialog.dialog('close');
            });
        };


        $scope.disabledTaLibFieldFunction = function (p) {
            if ($scope.titleModale === "Edition d'un Profil") {
                $scope.objetProfil.disabledEdit = true;
            }
        };

        $scope.disabledTaFieldFunctionForCode = function (p) {

        };

        $scope.changePageSize = function () {
            var url = listeProfilURL + "/" + $scope.pageSizeSelect + "/" + firstPage;
            //  var url = listeProfilURLs + "/" + $scope.initialNumerOfElements + "/" + firstPage;

            // var url = listeProfilURLs + "/" + pageSizeSelect + "/" + $scope.memoryPage;
            $scope.searchObject = angular.copy($scope.searchObjectMaster);
            $scope.searchObject = angular.copy($scope.searchObjectMaster);
            //   if ($scope.searchMotCle && $scope.searchMotCle != null) {
            if ($scope.searchMotCle !== null) {
                $scope.searchObject.mc = $scope.searchMotCle;
            }
            var url = listeProfilURL + "/" + $scope.pageSizeSelect + "/" + firstPage;
            GenericService.get(url + "/?mc=" + $scope.searchObject.mc)
                    .then(
                            function (data) {
                                $scope.listeObjetProfil = data.listProfil.content;
                                $scope.listPage = data.listProfil;
                                $scope.pageSizes = data.pageSize;
                                //$scope.pageSizeSelect = $scope.pageSizes[0];
                                $scope.sequence = data.sequence;
                                $scope.totalElements = data.listProfil.totalElements;
                                jslog("LISTE EMPLACEMENT = " + angular.toJson(data.totalElements));


                            },
                            function () {
                            }
                    );
        };


        $scope.paginate = function (pageSizeSelect, listePage) {
            $scope.memoryPage = listePage;
            //  var url = listeProfilURLs + "/" + $scope.initialNumerOfElements + "/" + firstPage;

            var url = listeProfilURL + "/" + pageSizeSelect + "/" + $scope.memoryPage;
            jslog("pageSizeSelect:" + angular.toJson(pageSizeSelect));
            jslog("$scope.memoryPage:" + angular.toJson($scope.memoryPage));
            $scope.searchObject = angular.copy($scope.searchObjectMaster);
            $scope.searchObject = angular.copy($scope.searchObjectMaster);
            //   if ($scope.searchMotCle && $scope.searchMotCle != null) {
            if ($scope.searchMotCle !== null) {
                $scope.searchObject.mc = $scope.searchMotCle;
            }
            var url2 = url + "/?mc=" + $scope.searchObject.mc;
            //     var url = listeProfilURLs + "/" + $scope.initialNumerOfElements + "/" + firstPage;
            //   "/?mc=" + $scope.searchObject.mc
            // jslog("url : "+url2);
            GenericService.get(url2)
                    .then(
                            function (data) {
                                $scope.listeObjetProfil = data.listProfil.content;
                                $scope.listPage = data.listProfil;
                                $scope.pageSizes = data.pageSize;
                                $scope.pageSizeSelect = $scope.pageSizes[0];
                                $scope.sequence = data.sequence;
                                $scope.totalElements = data.listProfil.totalElements;
                                jslog("LISTE EMPLACEMENT = " + angular.toJson($scope.listeEmplacements));
                            },
                            function () {

                            }
                    );
        };

        $scope.changerEtats = function (idAction, pr) {
            console.log(angular.toJson(pr));
            var workflowObjectOpp = {
                idObjet: pr.id,
                idAction: idAction

            };
            console.log(angular.toJson(workflowObjectOpp));
            //showConfirmation("changer l'état ", function () {
            GenericService.post(changerEtatURL, angular.toJson(workflowObjectOpp))
                    .then(
                            function (data) {
                                $scope.paginate($scope.pageSizeSelect, $scope.memoryPage);
//                                if (data.msg.statut !== '400') {
//                                    $scope.detailProfil(pr.id);
//                                    $scope.objetProfil = angular.copy(data.dto);
//                                    $scope.paginate($scope.pageSizeSelect, $scope.memoryPage);
//                                }
                            },
                            function (errResponse) {

                                console.info(errResponse);
                                
                               
                                   alert("alert");
                                    
                                    //notify();
                                    //$.frontValidationMessages
                                   

                            }
                    );
            //});

        };

        $scope.displaySaveTa = function () {
            return $scope.displaySaveButton;
        };
        $scope.displayCancelTa = function () {
            return $scope.displayCancelButton;
        };
        $scope.displayExitTa = function () {
            return $scope.displayExitButton;
        };

        //===========================================
        $scope.displayWkfTa = function (profil) {

            return (profil.id !== null) && (profil.isObsolete === false) && ($scope.displayWkf === true);
        };
        $scope.displayWkfTaList = function (profil) {
            return (profil.id !== null) && (profil.isObsolete === false);
        };
//=====================================


        $scope.disabledTaFieldFunction = function (pr) {
            if (pr.isActif) {
                return true;
            } else {
                return pr.disabledEdit;
            }
        };
        $scope.disabledTaFieldFunctionForCode = function (pr) {
            if (pr.isActif) {
                return true;
            }
            return pr.disabledEdit;
        };



        $scope.getCodeByHabilitationFoncton = function (habi) {

            alert(JSON.stringify(habi));

        };
        $scope.habilitation = "";
        $scope.videHabilitation = function () {
            $scope.listeObjetHabilitationFonctionnelle = [];
            objetHabilitationFonctionnelle = null;
        };


        $scope.vide = function () {
            $scope.objetHabilitationFonctionnelle.codeFonction = null;
            $scope.objetHabilitationFonctionnelle.idNiveauHabilitation = null;
        };

        $scope.saveHabilitationFonctionnelle = function (objetHabilitationFonctionnelle) {
            var codeFonction = document.querySelector('#codeFonction');
            var idNiveauHabilitation = document.querySelector('#idNiveauHabilitation');
            objetHabilitationFonctionnelle.codeFonction = codeFonction.options[codeFonction.selectedIndex].value;
            objetHabilitationFonctionnelle.idNiveauHabilitation = idNiveauHabilitation.options[idNiveauHabilitation.selectedIndex].value;
            jslog("Affichage:" + codeFonction.options[codeFonction.selectedIndex].value);
            $scope.objetHabilitationFonctionnelle.idProfil = $scope.objetProfil.id;
            $scope.objetHabiFoncMaster.idProfil = $scope.objetHabilitationFonctionnelle.idProfil;
            $scope.objetHabiFoncMaster.codeFonction = $scope.objetHabilitationFonctionnelle.codeFonction;
            $scope.objetHabiFoncMaster.habilitation = $scope.objetHabilitationFonctionnelle.idNiveauHabilitation;

            GenericService.post(saveHabiliFoncURL, angular.toJson(objetHabilitationFonctionnelle))
                    .then(
                            function () {
                                $scope.listeHabilitationFonctionnelles($scope.objetHabilitationFonctionnelle.idProfil);
                                $scope.listeDesFonctionnalite();
                                    $scope.listeDesHabilitation();
                                objetHabilitationFonctionnelle.codeFonction = null;
                                objetHabilitationFonctionnelle.idNiveauHabilitation = null;

                                $scope.vide();
                                
                            },
                            function () {

                            }

                    );


        };

        $scope.deleteHabilitationFonctionnelle = function (habilitation) {



            var dialog = $('#myModal').modal('show');
            $('#btnYes').click(function () {
                //dialog.dialog('close');
                GenericService.delete(deleteHabiliFonctURL + "/" + habilitation.id)
                        .then(
                                function (data) {
                                    //if (data) {
                                    $scope.listeHabilitationFonctionnelles($scope.objetProfil.id);
                                    $scope.listeDesFonctionnalite();
                                    $scope.listeDesHabilitation();
                                    //$scope.listeDesHabilations(null);
                                    $scope.vide();
                                    $('#myModal').modal('hide');
                                    //}
                                },
                                function () {
                                }
                        );
            });
            $('#btnNo').click(function () {
                //dialog.dialog('close');
            });
            // showConfirmation1("Supprimer", function () {
//                GenericService.delete(deleteHabiliFonctURL + "/" + idHabiliFonc)
//                        .then(
//                                function (data) {
//                                    if (data) {
//                                        $scope.listeHabilitationFonctionnelles($scope.objetHabilitationFonctionnelle.idProfil);
//                                        $scope.listeDesFonctionnalite();
//                                        //$scope.listeDesHabilations(null);
//                                        $scope.vide();
//                                    }
//                                },
//                                function () {
//                                }
//                        );
//                $scope.vide();
//                $scope.listeDesFonctionnalite();
//                $scope.listeDesHabilations(null);
            // });
        };



        $scope.upDateHabilitationFonctionnelle = function (objet) {

            if (objet !== null && objet !== undefined) {
                GenericService.post(saveHabiliFoncURL, angular.toJson(objet))
                        .then(
                                function (data) {
                                    if (data) {
                                        $scope.listeHabilitationFonctionnelles($scope.objetProfil.id);
                                        $scope.listeDesFonctionnalite();
                                        // $scope.listeDesHabilations(null);
                                    }
                                },
                                function () {
                                }
                        );
                $scope.vide();
                $scope.listeDesFonctionnalite();
                //$scope.listeDesHabilations(null);
            }

        };
        /*  $scope.paginate = function (pageSizeSelect, listePage) {
         $scope.memoryPage = listePage;
         var url = listeProfilURLs + "/" + pageSizeSelect + "/" + $scope.memoryPage;
         GenericService.get(url)
         .then(
         function (data) {
         $scope.listeObjetProfil = data.listeProfils.content;
         $scope.listPage = data.listeProfils;
         $scope.pageSize = data.pageSize;
         $scope.sequence = data.sequence;
         },
         function () {
         
         }
         );
         };*/

        $scope.listeDesProfil();

        $scope.paginateH = function (idProfil, pageSizeSelects, listePages) {
            //   $scope.paginerHabi = function (pageSizeSelects, listePages) {
            $scope.memoryPages = listePages;

            var url = listeHabiliFonctionnelleURL + "/" + idProfil + "/" + pageSizeSelects + "/" + $scope.memoryPages;
            GenericService.get(url)
                    // GenericService.get()
                    .then(
                            function (data) {
                                $scope.listeObjetHabilitationFonctionnelle = data.listHabilitation.content;
                                $scope.listPageH = data.listHabilitation;
                                $scope.pageSizesH = data.pageSizes;
                                $scope.pageSizeSelectH = $scope.pageSizes[0];
                                $scope.sequenceH = data.sequences;


                                $scope.totalElementsH = data.listHabilitation.totalElements;
                                jslog("LISTE EMPLACEMENT = " + angular.toJson($scope.listeEmplacements));
                            },
                            function () {

                            }
                    );
        };

        $scope.changePageHabiliSize = function () {
            var url = listeHabiliFonctionnelleURL + "/" + $scope.objetHabilitationFonctionnelle.idProfil + "/" + $scope.pageSizeSelects + "/" + firstPages;
            GenericService.get(url)
                    .then(
                            function (data) {
                                $scope.listeObjetHabilitationFonctionnelle = data.listeHabilitationFonctionnelles.content;
                                $scope.listePages = data.listeHabilitationFonctionnelles;
                                $scope.pageSizes = data.pageSizes;
                                // $scope.pageSizeSelects = $scope.pageSizes[0];
                                $scope.sequences = data.sequences;
                            },
                            function () {
                            }
                    );
        };


        $scope.showButtonSave = function (h) {
            return h.disabled === false;
        };

        $scope.showButtonEdit = function (h) {
            return h.disabled === true;
        };

        $scope.showButtonDelete = function (h) {
            return h.etat === false;

        };
        $scope.showModif = function () {


        };

        $scope.viderEdit = function () {
            $scope.objetProfil.code = null;
            $scope.objetProfil.intitule = null;

        };

        $scope.resetComboListe = function (objetProfil) {
            $scope.vide();
            $scope.listeDesFonctionnalite();
            $scope.listeDesHabilations(null);
            return objetProfil;
        };


        //================================================
        $scope.displayEditTa = function (profil) {
            return (profil.isEnSaisie == true || profil.isActif == true);
        };
        $scope.displayDeleteTa = function (profil) {
            return profil.isEnSaisie == true;
        };


        $scope.displaySaveTa = function () {
            return $scope.displaySaveButton;
        };
        $scope.displayCancelTa = function () {
            return $scope.displayCancelButton;
        };
        $scope.displayExitTa = function () {
            return $scope.displayExitButton;
        };

//===========================================
        $scope.displayWkfP = function (profil) {

            return (profil.id != null) && (profil.isObsolete == false) && ($scope.displayWkf == true);
        };
        $scope.displayWkfTaList = function (profil) {
            return (profil.id !== null) && (profil.isObsolete === false);
        };
//=====================================

        $scope.getStatut = function () {
            // $scope.objetProfil.statutDto.libelle = "here";

            if ($scope.objetProfil.statutDto.libelle !== null) {
                alert(JSON.stringify($scope.objetProfil.statutDto.libelle));
            }

        };
        $scope.getHabilitation = function (habi) {

            alert(angular.toJson(habi));

        };

        $scope.editerProfils = function (id) {
            //$scope.list = true;
            $scope.titleModale = "Edition d'un profil";
            $('#idProfile').val($scope.objetProfil.id);
            //   $scope.pagi = true;
            $scope.disabledCode = true;
            $scope.disabledLibelle = false;
            $scope.saveShow = true;
            $scope.closeShow = true;
            $scope.modalClose = false;
            $scope.tableau = true;
            $scope.tableHabilitation = true;
            $scope.disab = false;
            disabDelete = false;
            $scope.ligne = true;
            $scope.listeHabilitationFonctionnelles($scope.objetHabilitationFonctionnelle.idProfil);
            $scope.objetHabilitationFonctionnelle.idProfil = $scope.objetProfil.id;
            $('#idProfile').val($scope.objetHabilitationFonctionnelle.idProfil);
            //  $scope.vide();
            if (id !== null && id !== undefined) {
                GenericService.get(detailProfilURL + "/" + id)
                        .then(
                                function (data) {
                                    if (data) {
                                        $scope.objetProfil = data.detailProfil;
                                        $scope.objetProfil.disabledEdit = false;
                                        $scope.displaySaveButton = true;
                                        $scope.displayCancelButton = true;
                                        $scope.displayExitButton = false;
                                        $scope.displayWkf = false;
                                        $scope.objetHabilitationFonctionnelle.idProfil = $scope.objetProfil.id;
                                        $scope.objetProfil = data.detailProfil;
                                        $('#idProfile').val($scope.objetProfil.id);
                                        $scope.displayWkf = true;
                                        $scope.listeHabilitationFonctionnelles($scope.objetProfil.id);
                                    }
                                    /* if($scope.objetProfil.flgProfil === "4"){
                                     $scope.check();
                                     }
                                     if($scope.objetProfil.flgProfil ==="5" || $scope.objetProfil.flgProfil === null || $scope.objetProfil.flgProfil == undefined){
                                     
                                     }*/
                                    $scope.check($scope.objetProfil.flgProfil);
                                },
                                function () {
                                }

                        );

                if ($scope.titleModale === "Edition d'un Profil") {
                }
                if ($scope.objetProfil.statutDto.libelle === "Obsolete") {
                    $scope.objetHabilitationFonctionnelle.idProfil = null;
                    $scope.sta = false;
                    $scope.titre = true;
                    $scope.tableau = false;
                    $scope.tableHabilitation = false;

                } else if ($scope.objetProfil.statutDto.libelle === "Actif") {
                    $scope.sta = true;
                    $scope.titre = true;
                    $scope.tableau = true;
                    $scope.tableHabilitation = true;
                } else if ($scope.objetProfil.statutDto.libelle === "En saisie") {
                    $scope.sta = true;
                    $scope.titre = true;
                    $scope.tableau = true;
                    $scope.tableHabilitation = true;
                }
                if ($scope.objetProfil.statutDto.libelle === null) {
                    $scope.sta = true;
                    $scope.titre = true;
                    $scope.tableau = false;
                    $scope.tableHabilitation = false;
                }
                $scope.objetHabilitationFonctionnelle = $scope.objetProfil;

                if ($scope.objetProfil.flgProfil === "4") {
                    $scope.check();
                }
            }
        };


        $scope.editerProfil = function (id) {
            //$scope.list = true;
            $scope.titleModale = "Edition d'un profil";
            $('#idProfile').val($scope.objetProfil.id);
            //   $scope.pagi = true;
            $scope.disabledCode = true;
            $scope.disabledLibelle = false;
            $scope.saveShow = true;
            $scope.closeShow = true;
            $scope.modalClose = false;
            $scope.tableau = true;
            $scope.tableHabilitation = true;
            $scope.disab = false;
            $scope.ligne = true;
            $scope.listeHabilitationFonctionnelles($scope.objetHabilitationFonctionnelle.idProfil);
            $scope.objetHabilitationFonctionnelle.idProfil = $scope.objetProfil.id;
            $('#idProfile').val($scope.objetHabilitationFonctionnelle.idProfil);
            //  $scope.vide();
            if (id !== null && id !== undefined) {
                GenericService.get(detailProfilURL + "/" + id)
                        .then(
                                function (data) {
                                    if (data) {
                                        $scope.objetProfil = data.detailProfil;
                                        $scope.objetProfil.disabledEdit = false;
                                        $scope.displaySaveButton = true;
                                        $scope.displayCancelButton = true;
                                        $scope.displayExitButton = false;
                                        $scope.displayWkf = false;
                                        $scope.objetHabilitationFonctionnelle.idProfil = $scope.objetProfil.id;
                                        $scope.objetProfil = data.detailProfil;
                                        $('#idProfile').val($scope.objetProfil.id);
                                        $scope.displayWkf = true;
                                        $scope.listeHabilitationFonctionnelles($scope.objetProfil.id);
                                    }
                                    /* if($scope.objetProfil.flgProfil === "4"){
                                     $scope.check();
                                     }
                                     if($scope.objetProfil.flgProfil ==="5" || $scope.objetProfil.flgProfil === null || $scope.objetProfil.flgProfil == undefined){
                                     
                                     }*/
                                    $scope.check($scope.objetProfil.flgProfil);
                                },
                                function () {
                                }

                        );

                if ($scope.titleModale === "Edition d'un Profil") {
                }
                if ($scope.objetProfil.statutDto.libelle === "Obsolete") {
                    $scope.objetHabilitationFonctionnelle.idProfil = null;
                    $scope.sta = false;
                    $scope.titre = true;
                    $scope.tableau = false;
                    $scope.tableHabilitation = false;

                } else if ($scope.objetProfil.statutDto.libelle === "Actif") {
                    $scope.sta = true;
                    $scope.titre = true;
                    $scope.tableau = true;
                    $scope.tableHabilitation = true;
                } else if ($scope.objetProfil.statutDto.libelle === "En saisie") {
                    $scope.sta = true;
                    $scope.titre = true;
                    $scope.tableau = true;
                    $scope.tableHabilitation = true;
                }
                if ($scope.objetProfil.statutDto.libelle === null) {
                    $scope.sta = true;
                    $scope.titre = true;
                    $scope.tableau = false;
                    $scope.tableHabilitation = false;
                }
                $scope.objetHabilitationFonctionnelle = $scope.objetProfil;

                if ($scope.objetProfil.flgProfil === "4") {
                    $scope.check();
                }
            }
        };
        $scope.showPanelProfil = function () {
            return $scope.objetProfil !== null && $scope.objetProfil.id !== null;
        };

        $scope.modeEdition = 0;
        $scope.addProfil = function () {
           // alert("111");
           $scope.disableCode = false;
           $scope.disable = false;
            $scope.modeEdition = 0;
            $scope.titleModale = "Ajouter d'un profil";

            $('#detail-profil').modal('show');
        };

        $scope.detailProfilV = function (id) {
            $scope.modeEdition = 1;
            $scope.titleModale = "Détail d'un profil";
            $scope.disable = true;
            $scope.disableCode = true;
            GenericService.get(detailProfilURL + "/" + id)
                    .then(
                            function (data) {
                                if (data) {
                                    $scope.objetProfil = data.profilDTO;
                                    // $scope.objetProfil.disabledEdit = false;
                                    $scope.displaySaveButton = true;
                                    $scope.displayCancelButton = true;
                                    $scope.displayExitButton = false;
                                    $scope.displayWkf = false;
                                    $scope.objetHabilitationFonctionnelle.idProfil = $scope.objetProfil.id;
                                    $scope.displayWkf = true;
                                    $scope.listeHabilitationFonctionnelles($scope.objetProfil.id);
                                    $('#detail-profil').modal('show');
                                }
                                /* if($scope.objetProfil.flgProfil === "4"){
                                 $scope.check();
                                 }
                                 if($scope.objetProfil.flgProfil ==="5" || $scope.objetProfil.flgProfil === null || $scope.objetProfil.flgProfil == undefined){
                                 
                                 }*/
                                //$scope.check($scope.objetProfil.flgProfil);
                            },
                            function () {
                            }

                    );
            //$('#detail-profil').modal('show');
        };


        $scope.editProfilV = function (id) {
            
            $scope.detailProfilV(id);
            $scope.modeEdition = 1;
            $scope.titleModale = "Modification d'un profil";
            $scope.disable = false;
            $scope.disableCode = true;
        };

    }]);

