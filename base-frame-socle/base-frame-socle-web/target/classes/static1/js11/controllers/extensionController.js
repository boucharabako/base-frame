/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var App;
var appUrl;
App.controller('extensionController', ['$scope', 'GenericService', function ($scope, GenericService) {

        //        ===============================DEBUT PROPRIETES ETENDUS============================
        var urlDeBase = appUrl + "extension/";
        var conceptUrl = urlDeBase + "allConcept";
        var proprietiesUrl = urlDeBase + "allProprieties";
        var typeDonneesUrl = urlDeBase + "allTypeDonnees";
        var controleUrl = urlDeBase + "allListeControle";
        var classificationUrl = urlDeBase + "allClassification";
        var obligatoireUrl = urlDeBase + "allObligatoire";
        //var conceptUrl = urlDeBase + "allConcept";
        var deleteProprieteUrl = urlDeBase + "delete";
        var saveProprieteUrl = urlDeBase + "savePropriete";
        var detailProprieteUrl = urlDeBase + "detailPE";
        var changeStatutProUrl = urlDeBase + "changeStatutPro";



        var firstPage = 1;
        var initialNumerOfElements = 5;

        $scope.pageSizeSelectProp=5;
        $scope.pageSize = [];
        $scope.sequence = [];
        $scope.selectedPageSize = null;
        $scope.paginationObject = {selectedPageSize: ""};
        $scope.pager = {startPage: "", endPage: "", totalPages: "", currentPage: "", buttonsToShow: ""};
        $scope.listePage = {number: ""};
        $scope.searchObject = null;
        $scope.conceptMetierProprieteEtendu = $('#conceptMetierPE').val();

        $scope.proprieteObject = {id: null, ide: null, libelle: null, caption: null, codeLangue: null, etat: $scope.etatEnSaisie, tracking: [etatEnSaisie], disabledEdit: false, statut: null};
        $scope.proprieteObjectMaster = {id: null, ide: null, libelle: null, caption: null, codeLangue: null, etat: $scope.etatEnSaisie, tracking: [etatEnSaisie], disabledEdit: false, statut: null};
        $scope.statut = {id: null, libelle: null, ideCodeListe: null, action: null, statutTextColor: null, libelleStatutSuivant: null, ideCodeStatutSuivant: null, nextStatutBackColor: null, nextStatutTextColor: null, statutBackColor: null};

        $scope.listeConcept = [];
        $scope.listProprietes = [];
        $scope.listeTypeDonnees = [];
        $scope.listeClassification = [];
        $scope.listeControle = [];
        $scope.listeObligatoire = [];
        $scope.listeConcept = [];
        $scope.conceptSelected = null;
        $scope.listeExtension = [];

        $scope.displaySaveButton = true;
        $scope.displayCancelButton = true;
        $scope.displayExitButton = false;
        $scope.etatWkf = false;
        $scope.consult = 1;
//        $scope.motCleProp = {mc: ""};
        $scope.motCleProp = null;

        $scope.loadListConcept = function () {
            GenericService.get(conceptUrl)
                    .then(
                            function (data) {
                                $scope.listeConcept = data.listeConcept;
                                jslog("Les concepts " + angular.toJson($scope.listeConcept));
                            },
                            function () {}
                    );

        };
        $scope.loadListConcept();

        $scope.paginate = function (selectedPage, page, motCleProp) {
            jslog("PAGESELECTED+++++" + selectedPage);
            jslog("PAGE+++++" + page);
            var finalURL = proprietiesUrl + "/" + selectedPage + "/" + page;
            if (motCleProp != null && motCleProp != '') {
                finalURL = finalURL + '?motCleProp=' + motCleProp;
            }
            GenericService.get(finalURL)
                    .then(
                            function (data) {
                                $scope.listProprietes = data.listProprietes.content;
                                console.log("PROPRIETE*************" + angular.toJson($scope.listProprietes));
                                jslog("URL PROPRIETE " + angular.toJson(finalURL));
                                $scope.listePage = data.listProprietes;
//                                   $scope.listPage = data.listProprietes;
                                $scope.pageSize = data.pageSize;
                                $scope.sequence = data.sequence;
//                                $scope.pageSizeSelectProp = $scope.pageSize[0];
                                jslog("$scope.pageSize " + angular.toJson($scope.pageSize));
                                jslog("$scope.sequence " + angular.toJson($scope.sequence));
                                jslog("$scope.pageSizeSelect " + angular.toJson($scope.pageSizeSelectProp));
                            },
                            function () {}
                    );

        };
        $scope.paginate(initialNumerOfElements, firstPage, $scope.motCleProp);

        $scope.loadListPropriete = function () {
            $scope.paginate(initialNumerOfElements, firstPage, $scope.motCleProp);
//             $scope.isDisabled = null;
//            jslog("COOOOOOOOOOOOOOOOOOOURL" + finalURL);
        };



        $scope.searchPropriete = function () {
            $scope.paginate($scope.pageSizeSelectProp, firstPage, $scope.motCleProp);
        };


//        $scope.changePageSize = function (pageSizeSelect, motCleProp) {
        $scope.changePageSize = function (motCleProp) {
            var urlPage = proprietiesUrl + "/" + $scope.pageSizeSelectProp + "/" + firstPage;
            if (motCleProp != null && motCleProp != '') {
                urlPage = urlPage + '?motCleProp=' + motCleProp;
            }
            GenericService.get(urlPage)
                    .then(
                            function (data) {
                                $scope.listProprietes = data.listProprietes.content;
                                $scope.listePage = data.listProprietes;
                                $scope.sequence = data.sequence;
                                jslog("URLPAGESIZE         = " + angular.toJson(urlPage));
                            },
                            function () {
                            }
                    );
        };

        $scope.loadTypeDonnees = function () {
            GenericService.get(typeDonneesUrl)
                    .then(
                            function (data) {
                                $scope.listeTypeDonnees = data.listeTypeDonnees;
                                jslog("Les types de données+++++++ " + angular.toJson($scope.listeTypeDonnees));
                            }
                    );
        };
        $scope.loadTypeDonnees();

        $scope.loadClassification = function () {
            GenericService.get(classificationUrl)
                    .then(
                            function (data) {
                                $scope.listeClassification = data.listeClassification;
                                jslog("Les classifications+++++++" + angular.toJson($scope.listeClassification));
                            }
                    );
        };
        $scope.loadClassification();

        $scope.loadControle = function () {
            GenericService.get(controleUrl)
                    .then(
                            function (data) {
                                $scope.listeControle = data.listeControle;
                                jslog("Liste de contrôle+++++++" + angular.toJson($scope.listeControle));
                            }
                    );
        };
        $scope.loadControle();

        $scope.loadObligatoire = function () {
            GenericService.get(obligatoireUrl)
                    .then(
                            function (data) {
                                $scope.listeObligatoire = data.listeObligatoire;
                                jslog("Liste obligatoire+++++++" + angular.toJson($scope.listeObligatoire));
                            }
                    );
        };
        $scope.loadObligatoire();


        $scope.deletePropriete = function (pr) {
            showConfirmation('supprimer cette propriété ' + '"' + pr.libelle + '"', function () {
                var suppProprieteUrl = deleteProprieteUrl + "/" + pr.id;
                GenericService.get(suppProprieteUrl)
                        .then(
                                function (data) {
                                    $scope.paginate($scope.pageSizeSelectProp, firstPage);
                                },
                                function (errResponse) {

                                }
                        );
            });
        }
        ;

        $scope.savePropriete = function (proprieteObject) {
            GenericService.post(saveProprieteUrl, angular.toJson(proprieteObject))
                    .then(
                            function (data) {
                                if (data.msg.statut !== '400') {
                                    $('#ajoutPropriete').modal('hide');
                                }
                                //$scope.listProprietes = [];
                                //$scope.listProprietes = data.listProprietes;
                                $scope.loadListPropriete();
                                $scope.displaySaveButton = true;
                                $scope.displayCancelButton = true;
                                $scope.isDisabled = false;
                                jslog("ENREGISTREMENT++++++++" + angular.toJson($scope.proprieteObject));
                            },
                            function () {

                            }
                    );

        };


        $scope.fermerModal = function () {
            $('#ajoutPropriete').modal('hide');
        };

        $scope.detailPRO = function (pe) {
//             pe.disabledEdit = true;
            $scope.titre = " Détail d'une propriété";
            if (pe.id != null) {
                jslog("IDPROPRIETE_DETAIL+++++++ " + angular.toJson(pe.id));
                var detatilUrl = detailProprieteUrl + "/" + pe.id;
                GenericService.get(detatilUrl)
                        .then(
                                function (data) {
                                    $scope.proprieteObject = angular.copy($scope.proprieteObjectMaster);
                                    $scope.proprieteObject = angular.copy(data.detailPr);
                                    $scope.displaySaveButton = false;
                                    $scope.displayCancelButton = false;
                                    $scope.displayExitButton = true;
                                    $scope.proprieteObject.disabledEdit = true;
                                    $scope.isDisabled = true;
                                    $scope.etatWkf = true;
                                    jslog("DDDDDDDDDD++++++++++PPPPPPPP");
                                    jslog("PROPRIETE_OBJECT++++++++++  " + angular.toJson(angular.copy(data.detailPr)));
                                },
                                function (errReponse) {

                                }
                        );
            }
            ;
        };

        $scope.editPropriete = function (proprieteObject) {
            $scope.titre = " Modification d'une  propriété étendue";
            var detatilUrl = detailProprieteUrl + "/" + proprieteObject.id;
            GenericService.get(detatilUrl)
                    .then(
                            function (data) {
                                $scope.proprieteObject = data.detailPr;
                                $scope.displaySaveButton = true;
                                $scope.displayCancelButton = true;
                                $scope.displayExitButton = false;
                                $scope.etatWkf = true;
                                $scope.isDisabled = true;
                                $scope.proprieteObject.disabledEdit = false;
                            },
                            function (errReponse) {
                            }
                    );
        };


        $scope.changerEtatPro = function (id, index) {
            showConfirmation("changer l'état ", function () {
                GenericService.post(changeStatutProUrl + "/" + id)

                        .then(
                                function (data) {
                                    if (data.msg.statut !== '400') {
                                        $('#ajoutPropriete').modal('hide');
                                    }
                                    $scope.loadListPropriete();
                                },
                                function (errResponse) {

                                }
                        );
            });

        };


        $scope.addNewPropriete = function () {
            $scope.titre = " Ajout de propriété";
            $scope.displaySaveButton = true;
            $scope.displayCancelButton = true;
            $scope.displayExitButton = false;
            $scope.proprieteObject.id = null;
            $scope.proprieteObject.ide = null;
            $scope.proprieteObject.libelle = null;
            $scope.proprieteObject.caption = null;
            $scope.proprieteObject.disabledEdit = false;
            $scope.proprieteObject.statut = null;
            $scope.proprieteObject.isActif = false;
            $scope.isDisabled = false;

            $scope.proprieteObject = angular.copy($scope.proprieteObjectMaster);
            $scope.etatWkf == false;
        };


        $scope.displayWkf = function (po) {
            return (po.id != null) && (po.isObsolete == false);
        };

        $scope.displayEdit = function (po) {
            return po.isEnSaisie == true;
        };
        $scope.displayDelete = function (po) {
            return po.isEnSaisie == true;
        }

        $scope.activerSaved = function () {
            return $scope.displaySaveButton;
        };
        $scope.activerCancel = function () {
            return $scope.displayCancelButton;
        };

        $scope.activerExit = function () {
            return $scope.displayExitButton;
        };

        $scope.cancelPropriete = function () {
            $scope.proprieteObject = angular.copy($scope.proprieteObjectMaster);
            $scope.fermerModal();

            console.log($scope.proprieteObject);
        };

        $scope.disabledPEFunction = function (pe) {
            return pe.disabledEdit;
        };

        $scope.disabledPEFunctionForCode = function (pe) {
            if (pe.isActif) {
                return true;
            }
            return pe.disabledEdit;
        };
        $scope.displayWkfPEList = function (pe) {
            return (pe.id != null) && (pe.isObsolete == false) && ($scope.etatWkf == true);
        };

        jslog("CONCEPT METIER++++++++++" + angular.toJson($scope.conceptMetierProprieteEtendu));



//==============GROUPE D'EXTENSION====================================================

        var allGroupeExtensionURL = urlDeBase + "allGroupeExtension";
        var saveGroupeExtensionURL = urlDeBase + "saveGrouprExtension";
        var deleteGroupeExtensionURL = urlDeBase + "deleteGroupeExtension";
        var changerStatutURL = urlDeBase + "changeStatutGE";
        var detailGeURL = urlDeBase + "detailGE";
        var allExtensionURL = urlDeBase + "allExtension";

        $scope.groupeExtensionObject = {id: null, titre: null, description: null, disabledEdit: false, extension: [], statut: null, conceptMetier: null};
        $scope.groupeExtensionObjectMaster = {id: null, titre: null, description: null, disabledEdit: false, extension: [], statut: null, conceptMetier: null};
        $scope.extensionObject = {id: null, libelle: null};
        $scope.extensionObjectMaster = {id: null, libelle: null};
        $scope.listePageGe = {number: ""};
        $scope.motcle = {mc: ""};

        $scope.listeGroupeExtension = [];
        $scope.conceptMetierGroupeExtension = $('#conceptMetierGE').val();


        $scope.paginateGe = function (selectedPage, page) {
            var geURL = allGroupeExtensionURL + "/" + selectedPage + "/" + page;
            jslog("GroupeURL01        = " + angular.toJson(geURL));
            GenericService.get(geURL + "?mc=" + $scope.motcle.mc)
                    .then(
                            function (data) {
                                $scope.listeGroupeExtension = data.listeGroupeExtension.content;
                                console.log("GROUPE EXTENSION*************" + angular.toJson($scope.listeGroupeExtension));
                                jslog("GroupeURL02        = " + angular.toJson(geURL));
                                $scope.listePageGe = data.listeGroupeExtension;
                                $scope.pageSize = data.pageSize;
                                $scope.sequence = data.sequence;
//                                $scope.pageSizeSelect = $scope.pageSize[0];
                            },
                            function () {}
                    );

        };
        $scope.paginateGe(initialNumerOfElements, firstPage);


        $scope.loadGroupeExtension = function ()
        {
            $scope.paginateGe(initialNumerOfElements, firstPage);
        };

        $scope.changePageSizeGe = function (pageSizeSelect) {

            var urlPageGe = allGroupeExtensionURL + "/" + pageSizeSelect + "/" + firstPage;
            jslog("GroupeURL03        = " + angular.toJson(urlPageGe));
            GenericService.get(urlPageGe + "?mc=" + $scope.motcle.mc)
                    .then(
                            function (data) {
                                jslog("GroupeURL04       = " + angular.toJson(urlPageGe));
                                $scope.listeGroupeExtension = data.listeGroupeExtension.content;
                                $scope.listePageGe = data.listeGroupeExtension;
                                $scope.sequence = data.sequence;
                            },
                            function () {
                            }
                    );
        };

        $scope.searchGroupeExtension = function () {
            $scope.paginateGe(initialNumerOfElements, firstPage);

            jslog("TTTTTTTTTTTTTTTTTTTT           = " + angular.toJson($scope.motcle.mc));
        };

        $scope.saveGroupeExtension = function (groupeExtensionObject) {
            jslog("SAVE GROUPE EXTENSION" + angular.toJson(groupeExtensionObject));
            GenericService.post(saveGroupeExtensionURL, angular.toJson(groupeExtensionObject))
                    .then(
                            function (data) {
                                $scope.loadGroupeExtension();
                                if (data.msg.statut != "400") {
                                    $('#ajoutGroupeExtension').modal('hide');
                                }
                                $scope.listeGroupeExtension = [];
                                $scope.listeGroupeExtension = data.listeGroupeExtension;
                                $scope.displaySaveButton = true;
                                $scope.displayCancelButton = true;
                            },
                            function () {}
                    );
        };

        $scope.addNewGroupeExtension = function () {
            $scope.titre = "Ajout de groupe d'extension";
            $scope.displaySaveButton = true;
            $scope.displayCancelButton = true;
            $scope.displayExitButton = false;
            $scope.groupeExtensionObject.id = null;
            $scope.groupeExtensionObject.titre = null;
            $scope.groupeExtensionObject.description = null;
            $scope.groupeExtensionObject.conceptMetier = null;
            $scope.groupeExtensionObject.extension = null;
            $scope.groupeExtensionObject.disabledEdit = false;
            $scope.groupeExtensionObject.statut = null;
            $scope.groupeExtensionObject.isActif = false;
            $scope.groupeExtensionObject = angular.copy($scope.groupeExtensionObjectMaster);
            $scope.etatWkf = false;
        };


        $scope.deleteGroupeExtension = function (groupeExtensionObject) {
            showConfirmation('supprimer ce groupe extension ' + '"' + groupeExtensionObject.titre + '"', function () {
                var suppGroupeExtensionUrl = deleteGroupeExtensionURL + "/" + groupeExtensionObject.id;
                GenericService.get(suppGroupeExtensionUrl)
                        .then(
                                function (data) {
                                    $scope.paginateGe($scope.pageSizeSelect, firstPage);
                                    jslog("DELETE+++++++++++++" + angular.toJson(groupeExtensionObject.id))
                                },
                                function (errResponse) {

                                }
                        );
            });
        }
        ;


        $scope.changerEtatGe = function (id, index) {
            showConfirmation("changer l'état ", function () {
                GenericService.post(changerStatutURL + "/" + id)

                        .then(
                                function (data) {
                                    if (data.msg.statut !== '400') {
                                        $('#ajoutGroupeExtension').modal('hide');
                                    }
                                    $scope.loadGroupeExtension();
                                },
                                function (errResponse) {

                                }
                        );
            });

        };
        ;
        $scope.cancelGroupeExtension = function () {
            $scope.groupeExtensionObject = angular.copy($scope.groupeExtensionObjectMaster);
            $scope.fermerModalGe();

            console.log($scope.groupeExtensionObject);
        };
        $scope.fermerModalGe = function () {
            $('#ajoutGroupeExtension').modal('hide');
        };

        $scope.detailGroupeExtension = function (grpe) {
            $scope.titre = " Détail d'un groupe d'extension";
            if (grpe.id != null) {
                jslog("ID_GROUPE_DETAIL+++++++ " + angular.toJson(grpe.id));
                var detailUrl = detailGeURL + "/" + grpe.id;
                GenericService.get(detailUrl)
                        .then(
                                function (data) {
//                                    $scope.groupeExtensionObject = angular.copy($scope.groupeExtensionObjectMaster);
                                    $scope.groupeExtensionObject = angular.copy(data.detailGrp);
                                    $scope.loadListeExtension(data.detailGrp.conceptMetier);
                                    $scope.loadListConcept();
                                    $scope.displaySaveButton = false;
                                    $scope.displayCancelButton = false;
                                    $scope.displayExitButton = true;
                                    $scope.etatWkf = true;
                                    $scope.groupeExtensionObject.disabledEdit = true;
                                    jslog("GROUPE EXTENSION++++++++++PPPPPPPP");
                                    jslog("GROUPE_EXTENSION_OBJECT++++++++++  " + angular.toJson(angular.copy(data.detailGrp)));
                                },
                                function (errReponse) {

                                }
                        );
            }
            ;
        };


        $scope.editGroupeExtension = function (groupeExtensionObject) {
            $scope.titre = " Modification d'un groupe d'extension";
            var detailGeUrl = detailGeURL + "/" + groupeExtensionObject.id;
            GenericService.get(detailGeUrl)
                    .then(
                            function (data) {
                                $scope.groupeExtensionObject = data.detailGrp;
                                $scope.loadListeExtension(data.detailGrp.conceptMetier);
                                $scope.displaySaveButton = true;
                                $scope.displayCancelButton = true;
                                $scope.displayExitButton = false;
                                $scope.etatWkf = true;
                                $scope.groupeExtensionObject.disabledEdit = false;
                            },
                            function (errReponse) {
                            }
                    );
        }
        ;

        $scope.disabledGEFunction = function (ge) {
            return ge.disabledEdit;
        };

        $scope.disabledGEFunctionForCode = function (ge) {
            if (ge.isActif) {
                return true;
            }
            return ge.disabledEdit;
        };
        $scope.displayWkfGEList = function (ge) {
            return (ge.id != null) && (ge.isObsolete == false) && ($scope.etatWkf == true);
        };

        $scope.displayWkfGe = function (po) {
            return (po.id != null) && (po.isObsolete == false);
        };
        jslog("CONCEPT METIER GROUPE EXTENSION++++++++++" + angular.toJson($scope.conceptMetierGroupeExtension));


        //=========================FIN GROUPE D'EXTENSION=============================================


        $scope.loadListeExtension = function (conceptMetier) {
            if (conceptMetier != null && conceptMetier != 'undefined') {
                jslog("CONCEP METIER     " + conceptMetier);

                GenericService.get(allExtensionURL + "/" + conceptMetier)
                        .then(
                                function (data) {
                                    $scope.listeExtension = data.listeExtension;
                                    jslog("LISTE DES EXTENSIONS" + angular.toJson($scope.listeExtension));
                                }
                        );

            }
        };

        $scope.extensionConfig = {
            plugins: ['remove_button'],
            create: false,
//                    maxItems: 100,
            onChange: function (value) {
//                        $scope.getListLigneBudSource();
            }
        };




        //====================================================== Extension==============================================================
        var urlDeBase2 = appUrl + "extension2/";
        var listeExtensionURL = urlDeBase2 + "listeExtension";
        var saveExtensionURL = urlDeBase2 + "saveExtension";
        var avancerWorkflowURL = urlDeBase2 + "avancementWorkFlow";
        var updateExtensionURL = urlDeBase2 + "updateExtension";
        var supprimerExtensionURL = urlDeBase2 + "supprimerExtension";
        var detailExtensionURL = urlDeBase2 + "detailExtension";
        var typeDonneeUrl = urlDeBase2 + "allTypeDonnees";
        var conceptMetierUrl = urlDeBase2 + "allConcept";
        var controleListeUrl = urlDeBase2 + "allListeControle";
        var proprieteListeUrl = urlDeBase2 + "allPropriete";
        var classificationListeUrl = urlDeBase2 + "allClassification";
        var changeStatutUrl = urlDeBase2 + "changeStatut";
        $scope.conceptMetierExtension = $('#conceptMetierExtension').val();

        $scope.etatEnSaisie = etatEnSaisie;
        $scope.etatActif = etatActif;
        $scope.etatObselete = etatObselete;

        var firstPage = 1;
        var initialNumerOfElements = 5;
        $scope.pageSizeSelect=5;
        $scope.pageSizes = [5, 10, 20];
        $scope.sequenceExt = [];
        $scope.selectedPageSize = null;

        $scope.paginationObject = {selectedPageSize: ""};
        $scope.pagerExt = {startPage: "", endPage: "", totalPages: "", currentPage: "", buttonsToShow: ""};
        $scope.listePageExt = {number: ""};
        $scope.statut = {id: null, libelle: null, ideCodeListe: null, action: null, color: null};

        $scope.buttonEnregistrer = null;
        $scope.buttonAnnuler = null;
        $scope.buttonFermer = null;
        $scope.buttonEnregistrerUpdate = null;

//        $scope.extension = {id: null, typeDonnee: null, typeDonneeLibelle: null, propriete: null, proprieteLibelle: null, listeControle: null, listeControleLibelle: null, classification: null, classificationLibelle: null, codeConcept: null, requis: false, disable: false, etat: $scope.etatEnSaisie, tracking: [etatEnSaisie]};
//        $scope.extensionMaster = {id: null, typeDonnee: null, typeDonneeLibelle: null, propriete: null, proprieteLibelle: null, listeControle: null, listeControleLibelle: null, classification: null, classificationLibelle: null, codeConcept: null, requis: false, disable: false, etat: $scope.etatEnSaisie, tracking: [etatEnSaisie]};
        $scope.extension = {id: null, typeDonnee: null, typeDonneeLibelle: null, propriete: null, proprieteLibelle: null, listeControle: null, listeControleLibelle: null, classification: null, classificationLibelle: null, codeConcept: null, requis: false, disable: false, statut: null};
        $scope.extensionMaster = {id: null, typeDonnee: null, typeDonneeLibelle: null, propriete: null, proprieteLibelle: null, listeControle: null, listeControleLibelle: null, classification: null, classificationLibelle: null, codeConcept: null, requis: false, disable: false, statut: null};

        $scope.extensionListe = [];
        $scope.selectedClassification = null;

        $scope.paginateExt = function (selectedPage, page, motCle) {
            jslog("TEST 123  " + selectedPage + "§§ " + page);
            var url = listeExtensionURL + "/" + selectedPage + "/" + page;
            if (motCle != null && motCle != '') {
                url = url + '?motCle=' + motCle;
            }
            GenericService.get(url)
                    .then(
                            function (data) {
                                $scope.extensionListe = data.listeExtension.content;
                                $scope.listePageExt = data.listeExtension;
                                $scope.pageSizes = data.pageSizes;
                                $scope.sequenceExt = data.sequence;
                            },
                            function () {
                            }
                    );
        };


        $scope.changePageSizeExt = function (motCle) {
            var url = listeExtensionURL + "/" + $scope.pageSizeSelect + "/" + firstPage;
            if (motCle != null && motCle != '') {
                url = url + '?motCle=' + motCle;
            }
            GenericService.get(url)
                    .then(
                            function (data) {
                                $scope.extensionListe = data.listeExtension.content;
                                $scope.listePageExt = data.listeExtension;
                                $scope.sequenceExt = data.sequence;
                            },
                            function () {
                            }
                    );
        };


        $scope.afficherListeExtension = function (motCle) {
            var url = listeExtensionURL + "/" + initialNumerOfElements + "/" + firstPage;
            if (motCle != null && motCle != '') {
                url = url + '?motCle=' + motCle;
            }
            GenericService.get(url)
                    .then(
                            function (data) {
                                $scope.extensionListe = data.listeExtension.content;
                                $scope.listePageExt = data.listeExtension;
                                $scope.sequenceExt = data.sequence;
                                //$scope.pageSizeSelect = $scope.pageSizes[0];
                                jslog(" ------------------Liste extension 123 " + angular.toJson($scope.extensionListe));
                                jslog(" ------------------Liste extension 123" + angular.toJson($scope.listePageExt));

                            },
                            function () {
                            }
                    );
        };
        $scope.research = function () {
            $scope.paginateExt($scope.pageSizeSelect, firstPage, $scope.motCle);
        };

        $scope.afficherListeExtension();
        $scope.titre = null;
        $scope.idDisable = null;
        $scope.isEnregistrement = null;

        // Ajouter d'une extension
        $scope.ajouterNouvelleExtension = function () {

            $scope.titre = " Ajout d'une extension";
            $scope.buttonEnregistrer = true;
            $scope.buttonEnregistrerUpdate = false;
            $scope.buttonAnnuler = true;
            $scope.buttonFermer = false;
            $scope.extension = angular.copy($scope.extensionMaster);
            $scope.mode = 1;
            $scope.idDisable = false;
            $scope.isEnregistrement = true;

        };
        $scope.fermerModalExtension = function () {
            $('#ajoutExtension').modal('hide');

        };
        //Enregistrement d'une extension//

        $scope.enregistrerExtension = function (extension) {
            $scope.extension.listeDeControle = extension.listeDeControle;
            $scope.extension.classification = extension.classification;
            jslog("EXTENSION DTO 1 ================ " + angular.toJson(extension));
            jslog("DANS MON ENREGISTREMENT++++++++++++++++++++++++++++++ ");

            GenericService.post(saveExtensionURL, angular.toJson(extension))
                    .then(
                            function (data) {
                                if (data.typeDonnee && data.typeDonnee != null) {
                                    $scope.fermerModalExtension();
                                }
                                $scope.afficherListeExtension();
                                $scope.buttonAnnuler = true;
                                $scope.buttonEnregistrer = true;
                                $scope.buttonEnregistrerUpdate = false;
                                $scope.mode = 1;
                                $scope.buttonFermer = false;
                                $scope.isEnregistrement = true;
                                // $scope.changerSave == 1;


                            },
                            function (errResponse) {
                                console.error(errResponse);
                            }
                    );
            console.log(extension);
        };
        $scope.avancerWorkflow = function (extension) {
            jslog("AVANCEMENT///////////////");
            GenericService.post(avancerWorkflowURL, angular.toJson(extension))
                    .then(
                            function (data) {
                            },
                            function (errResponse) {
                                console.error(errResponse);
                            }
                    );
            console.log(extension);
        };

        $scope.enregistrerExtensionUpdate = function (extension) {
            $scope.extension.listeDeControle = extension.listeDeControle;
            $scope.extension.classification = extension.classification;
            jslog("EXTENSION DTO 2 ================ " + angular.toJson(extension));
            jslog("DANS MA MODIFICATION++++++++++++++++++++++++++++++ ");
            GenericService.post(updateExtensionURL, angular.toJson(extension))
                    .then(
                            function (data) {
                                if (data.typeDonnee && data.typeDonnee != null) {
                                    $scope.fermerModalExtension();
                                }
                                $scope.afficherListeExtension();
                                $scope.buttonAnnuler = true;
                                $scope.buttonEnregistrerUpdate = true;
                                $scope.buttonEnregistrer = false;
                                $scope.mode = 1;
                                $scope.buttonFermer = false;
                                $scope.isEnregistrement = false;

                            },
                            function (errResponse) {
                                console.error(errResponse);
                            }
                    );
            console.log(extension);
        };

        $scope.supprimerExtension = function (extension)
        {
            if (extension.id !== null && extension.id !== undefined)
                showConfirmation('supprimer cette extension ', function () {

                    GenericService.get(supprimerExtensionURL + "/" + extension.id)
                            .then(
                                    function (data) {
                                        $scope.afficherListeExtension();
                                    },
                                    function (errResponse) {
                                        console.error('Error while fetching Currencies' + errResponse);
                                    }
                            );
                });
        };


        $scope.annulerExtension = function () {
            $scope.extension = angular.copy($scope.extensionMaster);
            console.log($scope.extension);
        };

        $scope.detailsExtension = function (extension) {

            GenericService.get(detailExtensionURL + "/" + extension.id)
                    .then(
                            function (data) {
                                $scope.titre = " Détail d'une extension";
                                $scope.extension = angular.copy(data.extensionVue);
                                jslog('extension++++++++' + angular.toJson(extension));
                                $scope.loadConceptMetier();
                                $scope.extension.disable = true;
                                $scope.buttonEnregistrer = false;
                                $scope.buttonEnregistrerUpdate = false;
                                $scope.buttonAnnuler = false;
                                $scope.buttonFermer = true;
                                $scope.idDisable = true;
                            },
                            function (errReponse) {
                            }
                    );

        };

        $scope.canEdit = function (extension) {
            //return (extension.isEnSaisie == true || extension.isActif == true);
            return extension.isEnSaisie == true;
        };
        $scope.canDelete = function (extension) {
            return extension.isEnSaisie == true;
        };

        $scope.loadTypeDonnees = function () {
            GenericService.get(typeDonneeUrl)
                    .then(
                            function (data) {
                                $scope.listeTypeDonnees = data.listeTypeDonnees;
                                jslog("Les types de données+++++++ " + angular.toJson($scope.listeTypeDonnees));
                            }
                    );
        };
        $scope.loadTypeDonnees();

        $scope.loadClassification = function () {
            GenericService.get(classificationListeUrl)
                    .then(
                            function (data) {
                                $scope.listeClassification = data.listeClassification;
                                jslog("Les classifications+++++++" + angular.toJson($scope.listeClassification));
                            }
                    );
        };
        $scope.loadClassification();

        $scope.listeConceptMetier = [];
        $scope.loadConceptMetier = function () {
            GenericService.get(conceptMetierUrl)
                    .then(
                            function (data) {
                                $scope.listeConceptMetier = data.listeConcept;
                                jslog("Les concepts+++++++" + angular.toJson($scope.listeConceptMetier));
                                jslog("Les concepts+++++++ size" + angular.toJson($scope.listeConceptMetier.length));
                            }
                    );
        };
        $scope.loadConceptMetier();

        $scope.loadControle = function () {
            GenericService.get(controleListeUrl)
                    .then(
                            function (data) {
                                $scope.listeControle = data.listeControle;
                                jslog("Liste de contrôle+++++++" + angular.toJson($scope.listeControle));
                            }
                    );
        };
        $scope.loadControle();

        $scope.listePropriete = [];
        $scope.loadPropriete = function () {
            GenericService.get(proprieteListeUrl)
                    .then(
                            function (data) {
                                $scope.listePropriete = data.listePropriete;
                                jslog("Liste des proprietes+++++++" + angular.toJson($scope.listePropriete));
                            }
                    );
        };


        $scope.activerListePropriete = function () {
            $scope.loadPropriete();
        };
//        $scope.$apply(function () {
//             $scope.loadPropriete();
//        });

        $scope.editExtension = function (extension)
        {
            GenericService.get(detailExtensionURL + "/" + extension.id)
                    .then(
                            function (data) {
                                $scope.titre = " Edition d'une extension";
                                $scope.extension = data.extensionVue;
                                $scope.extension.disable = false;
                                $scope.mode = 1;
                                $scope.buttonAnnuler = true;
                                $scope.buttonEnregistrer = false;
                                $scope.buttonEnregistrerUpdate = true;
                                $scope.buttonFermer = false;
                                $scope.idDisable = true;
                                $scope.isEnregistrement = true;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Currencies' + errResponse);
                            }
                    );
        };


        $scope.activerEnregistrer = function () {
            return $scope.buttonEnregistrer;
        };
        $scope.activerEnregistrerUpdate = function () {
            return $scope.buttonEnregistrerUpdate;
        };
        $scope.buttonAnnuler = null;
        $scope.activerAnnuler = function () {
            return $scope.buttonAnnuler;
        };
        $scope.buttonFermer = null;
        $scope.activerButtonFermer = function () {
            return $scope.buttonFermer;
        };


        $scope.switchChampListeControle = function () {
            if ($scope.extension.typeDonnee == $("#listeControle").val() || $scope.extension.typeDonnee == $("#listeMultiple").val())
                return  true;
        };
        $scope.switchChampClassification = function () {
            if ($scope.extension.typeDonnee == $("#classification").val()) {
                return  true;
            }
        };


        $scope.changerEtat = function (idExt, index) {
            showConfirmation("changer l'état ", function () {

                GenericService.post(changeStatutUrl + "/" + idExt)

                        .then(
                                function (data) {
                                    if (data.msg.statut !== '400') {
                                        $('#ajoutExtension').modal('hide');
                                    }
                                    $scope.afficherListeExtension();
                                },
                                function (errResponse) {

                                }
                        );
            });

        };
        $scope.displayStParam = false;
        $scope.displayStParamExt = function (ext) {
            return (ext.id != null) && (ext.isObsolete == false) && ($scope.displayStParam == true);
        };
        $scope.displayStParamList = function (ext) {
            return (ext.id != null) && (ext.isObsolete == false);
        };



    }]);
