/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *https://github.com/jagdeep-singh/angularMultipleSelect
 */

/*
 * Pour integrer ce fragment de gestion de cycle de vie via workflow
 * voir le exampleController.js
 */




var App;
var appUrl;
App.controller('wCycleVieControllerV3', ['$rootScope', '$scope', 'GenericService', 'PropagationService', function ($rootScope, $scope, GenericService, PropagationService) {
        var baseUrl = appUrl + 'api/utilisateurs/';
        var usersUrl = baseUrl + 'getUsersFromProfils';
        var extensionUrl = appUrl + 'api/groupe_extension/find-extensions';
        var modeCommentaireUrl = appUrl + 'api/ged/document/find-mode-comment';

        var profilAutoUrl = appUrl + 'api/parameter/autorisation/wf/stepAction';
        var initWorkflowURL = appUrl + 'api/parametre/fragment/conf/workflow-cycle/init-workflow-object';
        var initUsersURL = appUrl + 'api/parametre/fragment/conf/workflow-cycle/find-users';
        var initUsersURL2 = appUrl + 'api/ged/document/find-users-espcaDoc';
        var wfAvancerObjURL = appUrl + 'api/ged/document/executer-action';
        var wfAvancerObjProgrammeURL = appUrl + 'api/parameter/ref/budgetaire/programme/executer-action-programme';
        $scope.montant = 0;

        $scope.wfStepObjMaster = {"idobj": null, "idStep": null, libelleObjet: null, natureObjet: null,
            "comment": null, "action": null, "nextStep": null, "notifyUsers": false,
            selectedUsers: [], conceptMetier: null, espaceDoc: null};
        $scope.wfStepObj = angular.copy($scope.wfStepObjMaster);
        $scope.objectParams = {};
        $scope.item = null;
        $scope.userList = [];
        $scope.actionSelected = {};

        $scope.profiles = [];
        profilesGrp = [];

        $scope.workflowCycleParameter = {};
        $scope.workflowObjet = {};
        $scope.codeFonction = null;
        //Ajouter par Assima
        $scope.extensionList = [];

        $rootScope.initParameters = function (param) {

            jslog("$rootScope.initParameters: " + angular.toJson(param));
            console.info(param.executor);
            $scope.wfStepObj = angular.copy($scope.wfStepObjMaster);
            if (param && param.idObjet) {


                delete $scope.act;
                delete $scope.actionSelected.id;
                console.info(param);

                if (param.executor)
                {
                    console.info(param.executor.length);
                    $scope.objectParams.executor = param.executor;
                }

                $scope.wfStepObj.conceptMetier = param.conceptMetier;
                $scope.codeFonction = param.codeFonction;
                $scope.wfStepObj.idobj = param.idObjet;
                $scope.wfStepObj.natureObjet = param.natureObjet;
                $scope.wfStepObj.espaceDoc = param.espaceDoc;

                $scope.objectParams.idObjet = param.idObjet;
                $scope.objectParams.natureObjet = param.natureObjet;
                $scope.objectParams.conceptMetier = param.conceptMetier;
                //Ajouter par Assima
                $scope.objectParams.idGroupeExtension = param.idGroupeExtension;
                $scope.objectParams.action = param.action;
                $scope.objectParams.espaceDoc = param.espaceDoc;
                jslog("$rootScope.actionObject: " + angular.toJson(param.action));
                jslog("$rootScope.espaceDoc: " + angular.toJson(param.espaceDoc));
                jslog("$rootScope.initParameters 2 : " + angular.toJson($scope.objectParams));
                $scope.init();

                /**
                 * Ajouter pour prendre en compte l'avancement automatique du workflow
                 */
                if (param.reservationMode) {
                    showConfirmation("effectuer cette opération", function () {
                        $scope.avancerCycleVie($scope.wfStepObj, $scope.wfStepObj.action);
                    });
                } else {
                    $("#avancerWkFlow").modal('show');
                }
            }
        };
        //Ajouter par Assima
        $scope.getExtension = function (idGroupeExtension) {
            jslog(" =============================== RECHERCHE DES EXTENSIONS ===============================");
            GenericService.get(extensionUrl + "/" + idGroupeExtension)
                    .then(
                            function (data) {
                                jslog("LA LISTE DES Extensions  : " + angular.toJson(data));
                                $scope.extensionList = data.extensions;
                            },
                            function (errResponse) {
                            }
                    );
        };
        $scope.getUsers = function () {
            GenericService.post(usersUrl, angular.toJson($scope.profiles))
                    .then(
                            function (data) {
                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                $scope.userList = data.users;
                            },
                            function (errResponse) {
                            }
                    );
        };
//        $scope.getUsers = function () {
//            GenericService.post(usersUrl, angular.toJson($scope.profiles))
//                    .then(
//                            function (data) {
////                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
//                                $scope.userList = data.users;
//                            },
//                            function (errResponse) {
//                            }
//                    );
//        };


//        $scope.selectedList = [];

        $scope.isvalid = function () {
            return $scope.userList.length > 0;
        };
        $scope.init = function () {
            jslog("cycleVieController");
            $scope.extensionList = [];
            $scope.profiles = [];
            $scope.userList = [];
            $scope.wfStepObj = angular.copy($scope.wfStepObjMaster);

            $scope.wfStepObj.idobj = $scope.objectParams.idObjet;

//            $scope.wfStepObj.action = angular.element("#OneAction").val();
            $scope.wfStepObj.conceptMetier = $scope.objectParams.conceptMetier;
            $scope.wfStepObj.natureObjet = $scope.objectParams.natureObjet;
            $scope.wfStepObj.codeFonction = $scope.codeFonction;

            //Ajouter par Assima

            $scope.wfStepObj.idGroupeExtension = $scope.objectParams.idGroupeExtension;
            $scope.wfStepObj.action = $scope.objectParams.action.idActionPermise;
            $scope.wfStepObj.espaceDoc = $scope.objectParams.espaceDoc;

            if ($scope.wfStepObj.action !== null) {
                jslog("entree recherche user");
                $scope.selectWfAction($scope.objectParams.action, $scope.objectParams.idObjet, $scope.objectParams.espaceDoc);
            }



//            $scope.initWorkflowParams();

        };

        $scope.usersByAction = function (idObject, action, espaceDoc) {
            
            {
               
                jslog("cycleVieController action  : " + angular.toJson(action));
                GenericService.get(initUsersURL2 + "/" + idObject + "/" + action + "?e=" + espaceDoc)
                        .then(
                                function (data) {
                                    jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                    $scope.userList = data;

                                    console.log(data);
                                    $scope.userListGroupe();
                                },
                                function (errResponse) {
                                }
                        );
            }
        };
//        $scope.initWorkflowParams = function () {
//            jslog("initWorkflowParams wfStepObj: " + angular.toJson($scope.wfStepObj));
//
//            $scope.workflowCycleParameter.concepMetier = $scope.wfStepObj.conceptMetier;
//            $scope.workflowCycleParameter.idObjet = $scope.wfStepObj.idobj;
//            $scope.workflowCycleParameter.codeFonction = $scope.wfStepObj.codeFonction;
//
//
//            if ($scope.wfStepObj && $scope.wfStepObj.conceptMetier !== null && $scope.wfStepObj.idobj !== null) {
//                jslog("initWorkflowParams workflowCycleParameter: " + angular.toJson($scope.workflowCycleParameter));
//                GenericService.post(initWorkflowURL, angular.toJson($scope.workflowCycleParameter))
//                        .then(
//                                function (data) {
//                                    jslog("initWorkflowParams data " + angular.toJson(data));
//                                    $scope.workflowObjet = data;
//
//                                    if (data.actions && data.actions.length === 1) {
//                                        $scope.userList = data.utilisateursAutorises;
//                                        $scope.actionSelected = data.actions[0];
//                                        $scope.wfStepObj.action = $scope.actionSelected.id;
//                                        $scope.userListGroupe();
//                                    }
//                                    /*
//                                     * Aucune action permise
//                                     */
//                                    if (data.actions && data.actions.length === 0) {
////                                        $scope.msg = "Aucune action ne vous est permise à cet état!";
//                                        $scope.noActionError = angular.element('#noActionError').val();
//                                        jslog("$scope.noActionError : " + $scope.noActionError);
//                                        GenericService.frontValidationMessages("WARNING", $scope.noActionError);
//                                    }
//                                },
//                                function (errResponse) {
//                                    jslog("initWorkflowURL errResponse : " + angular.toJson(errResponse));
//                                }
//                        );
//            }
//        };

        $scope.userListGroupe = function () {
            if ($scope.userList) {

                angular.forEach($scope.userList, function (val) {
                    if (!$scope.checkedIfPresent(profilesGrp, val)) {
                        profilesGrp.push({profil: val.profil, libelleProfil: val.libelleProfil});
                    }

                });

            }

            return [];
        };
        $scope.checkedIfPresent = function (liste, val) {
            if ($scope.userList) {
                var l = [];
                var flag = false;
                angular.forEach(liste, function (v) {


                    if (val.profil === v.profil)
                        flag = true;

                });
                return   flag;
            }
        };

        $scope.selectWfAction = function (action, idObject, espaceDoc) {

            jslog("selectWfAction:  action: " + angular.toJson(action));
            if (action.idActionPermise && idObject && idObject != null && idObject != "") {
//                angular.forEach($scope.workflowObjet.actions, function (val) {
//                    if (val.id === action) {
                $scope.actionSelected = action;
                $scope.wfStepObj.action = $scope.actionSelected.idActionPermise;
                $scope.usersByAction(idObject, $scope.wfStepObj.action, espaceDoc);

                //Ajouter par Assima
                $scope.getIndicateurCommentaire(action.idActionPermise, idObject);
                jslog("selectWfAction:   $scope.actionSelected: " + angular.toJson($scope.actionSelected));
//                    }
//                });


            }
        };
        /**
         * Ajouter par Assima
         */
        $scope.modeEtendu = false;
        $scope.displayComment = false;
        $scope.getIndicateurCommentaire = function (action, idObject) {
            jslog(" ================================  FONCTION GET INDICATEUR COMMENTAIRE  :  =================================" + angular.toJson(idObject));
            $scope.diplayComment = false;
            $scope.modeEtendu = false;
            GenericService.get(modeCommentaireUrl + "/" + action + "/" + idObject)
                    .then(
                            function (data) {
                                jslog(" ================================  TYPE DE MODE ETENDU COMMENTTAIRE  :  =================================" + angular.toJson(data.modeEtendu));
                                jslog(" ================================  TYPE DE MODE ETENDU COMMENTTAIRE  :  =================================" + angular.toJson(data.idGroupeExtension));
                                $scope.modeEtendu = data.modeEtendu;
                                if (data.modeEtendu == true) {
//                                    if ($scope.objectParams.idGroupeExtension && $scope.objectParams.idGroupeExtension != null) {
                                    if (data.idGroupeExtension && data.idGroupeExtension != null && data.idGroupeExtension != "") {
                                        $scope.getExtension(data.idGroupeExtension);
                                    }
                                    $scope.displayComment = true;
//                                    }
                                } else {
                                    $scope.extensionList = [];
                                }
                                if (data.modeBasique && data.modeBasique == true) {
                                    $scope.displayComment = true;
                                }
//                                else{
//                                    $scope.displayComment = false;
//                                }

                            },
                            function (errResponse) {
                            }
                    );

        };
        $scope.displayListeDeControleMultiple = function (ext) {
            var value = false;
            if (ext.typeDonneListDeControle && ext.typeDonneListDeControle != null && ext.typeDonnee == "M" && !ext.typeDonneClassification) {
                jslog("ENTREZ DANS LA FONCTION DE DISPLAY MULTIPLE");
                value = true;
            }
//            if (ext.typeDonneListDeControle && ext.typeDonneListDeControle != null && ext.typeDonnee == "M") {
//                jslog("ENTREZ DANS LA FONCTION DE DISPLAY MULTIPLE");
//                value = true;
//            }
            return value;
        };
        $scope.displayListeDeControleSelect = function (ext) {
            var value = false;
            if (ext.typeDonneListDeControle && !ext.codeListDeControleIsOUI_NON && ext.typeDonneListDeControle != null && ext.typeDonnee != "M" && !ext.typeDonneClassification) {
                value = true;
            }
//            if (ext.typeDonneListDeControle && !ext.codeListDeControleIsOUI_NON && ext.typeDonneListDeControle != null && ext.typeDonnee != "M") {
//                value = true;
//            }
            return value;
        };
        $scope.displayListeDeControleSwitch = function (ext) {
            var value = false;
            if (ext.typeDonneListDeControle && ext.codeListDeControleIsOUI_NON && ext.typeDonneListDeControle != null && ext.typeDonnee != "M" && !ext.typeDonneClassification) {
                value = true;
            }
//            if (ext.typeDonneListDeControle && ext.codeListDeControleIsOUI_NON && ext.typeDonneListDeControle != null && ext.typeDonnee != "M") {
//                value = true;
//            }
            return value;
        };

        $scope.displayClassificationField = function (ext) {
            var value = false;
            if (ext.typeDonneClassification != undefined && ext.typeDonneClassification) {
                value = true;
            }
            return value;
        };

        $scope.addSelectedValue = function (ext) {
//            splice(0, 0, angular.copy($scope.tiersDepensePieceUse))
//            ext.valeurMultipleChecked.splice(0, 0, angular.copy(idCodif));
            jslog("LES ELEMENT DE L'EXTENSION ");
            jslog("LES ELEMENT DE L'EXTENSION " + angular.toJson(ext));
            jslog("LES ELEMENT DE L'EXTENSION " + angular.toJson($scope.extensionList));
        };


        $scope.getProperteType = function (ideCode) {
            if (ideCode == "A" || ideCode == "X") {
                return "text";
            }
            if (ideCode == "N") {
                return "number";
            }
            if (ideCode == "D") {
                return "date";
            }
            return "";
        };

        $scope.displayCheckBox = function (ext) {
            return !ext.typeDonneListDeControle && !ext.typeDonneClassification;
//            return !ext.typeDonneListDeControle;
//            return (ideCode == "A" || ideCode == "X");
        };

        $scope.displayFieldNoneListControle = function (ext) {
            return !ext.typeDonneListDeControle && !ext.typeDonneClassification;
        };

        $scope.disabledInput = function (ext) { //!ext.cochet
//            if (typeDonnee == "A" || typeDonnee == "X") {
//                return true;
//            }
//            jslog("////////////////////////// "+ext.typeDonnee)
            return !ext.cochet && (ext.typeDonnee == "A" || ext.typeDonnee == "X" || ext.typeDonnee == "N" || ext.typeDonnee == "D");
        };

        $scope.canCheck = function (ext) {
            if (ext.cochet == false) {
                ext.valeur = "";
            }
        };
        $scope.dynamicCheck = function (ext) {
            if (ext.valeur = "") {
                ext.cochet = false;
            }
        };

        $scope.inputIsChecked = function (object) {
            if (object == true) {
                jslog("CHECKED " + object);
            }
            if (object == false) {
                jslog("CALEUR " + object.valeur)
                jslog("UNCHECKED " + object);
            }
        };

        $scope.avancerCycleVie = function (object, action) {
            jslog("cycleVieController: avancerCycleVie " + angular.toJson(object) +
                    " action : " + angular.toJson(action));

            $scope.workflowCycleParameter = {};
            $scope.workflowCycleParameter.concepMetier = object.conceptMetier;

            if ($scope.objectParams.executor && $scope.actionSelected && $scope.actionSelected.codeCallback !== null) {

                angular.forEach($scope.objectParams.executor, function (val) {

                    console.info(val);
                    if (val.code === $scope.actionSelected.codeCallback) {
                        $scope.workflowCycleParameter.paramsExecutor = val.paramsExecutor;
                        $scope.workflowCycleParameter.executor = val.code;
                    }
                });


            }

            $scope.workflowCycleParameter.codeFonction = $scope.codeFonction;
            $scope.workflowCycleParameter.idObjet = object.idobj;
//            $scope.workflowCycleParameter.idAction = (object.action) ? object.action : angular.element("#OneAction").val();
            $scope.workflowCycleParameter.idAction = (object.action) ? object.action : $scope.wfStepObj.action;
            $scope.workflowCycleParameter.commentaire = object.comment;
            $scope.workflowCycleParameter.users = $scope.wfStepObj.selectedUsers;
            //Ajouter par Assima DEBUT
            $scope.workflowCycleParameter.extensionsList = $scope.extensionList;
            $scope.workflowCycleParameter.espaceDoc = object.espaceDoc;
            //Ajouter par Assima FIN

            jslog("cycleVieController workflowCycleParameter  : " + angular.toJson($scope.workflowCycleParameter));
            jslog("utilisateurs  : " + angular.toJson($scope.workflowCycleParameter.users));

            GenericService.post(wfAvancerObjURL, angular.toJson($scope.workflowCycleParameter))
                    .then(
                            function (data) {
                                jslog("cycleVieController wfAvancerObjURL  : " + angular.toJson(data));
                                // Recharger la page
//                                window.location.reload();
                                //Diffuser un message pour indiquer la fin de l'operation en cours
                                PropagationService.setOperation(true);
                                PropagationService.setObject($scope.workflowCycleParameter);
                                $scope.init();
                                delete  $scope.execution;

//                                GenericService.frontValidationMessages("INFO", "Opération réussie");
                                $('#avancerWkFlow').modal("hide");
                            },
                            function (errResponse) {
                                jslog("errResponse: " + angular.toJson(errResponse));
                            }
                    );

//           
        };

        $scope.avancerCycleVieProgramme = function (object, action) {
            showConfirmation("effectuer cette opération", function () {
                $scope.workflowCycleParameter = {};
                $scope.workflowCycleParameter.concepMetier = object.conceptMetier;
                $scope.workflowCycleParameter.idObjet = object.idobj;
                $scope.workflowCycleParameter.idAction = (object.action) ? object.action : $scope.wfStepObj.action;
                $scope.workflowCycleParameter.commentaire = (object.comment != null) ? object.comment : $scope.workflowCycleParameter.idAction;
                $scope.workflowCycleParameter.users = [];
                console.info($scope.wfStepObj.selectedUsers);

                angular.forEach(object.selectedUsers, function (value, key) {
                    this.push(value.username);
                }, $scope.workflowCycleParameter.users);

                GenericService.post(wfAvancerObjProgrammeURL, angular.toJson($scope.workflowCycleParameter))
                        .then(
                                function (data) {
                                    //Diffuser un message pour indiquer la fin de l'operation en cours
                                    if (data.check == true) {
                                        $scope.question($scope.workflowCycleParameter);
                                    } else {
                                        PropagationService.setOperation(true);
                                        $scope.init();
                                    }
                                },
                                function (errResponse) {
                                    jslog("errResponse: " + angular.toJson(errResponse));
                                }
                        );
            });
        };

        $scope.question = function (object) {
            $('#wkf-confirm-modal').modal('show');
            PropagationService.setObject(object);
        };

        $scope.beforeSelectItem = function (item) {
            // perform operation on this item before selecting it.
            $scope.item = item;
            jslog("beforeSelectItem: " + angular.toJson(item));
        };

        $scope.afterSelectItem = function (item) {
            // perform operation on this item after selecting it.
            $scope.item = item;
            jslog("afterSelectItem" + angular.toJson(item));
        };

        $scope.beforeRemoveItem = function (item) {
            // perform operation on this item before removing it.
            $scope.item = item;
            jslog("beforeRemoveItem" + angular.toJson(item));
        };

        $scope.afterRemoveItem = function (item) {
            // perform operation on this item after removing it.
            $scope.item = item;
            jslog("afterRemoveItem" + angular.toJson(item));
        };

//        $('wkf-modal')
        $('#wkf-modal').on('hidden.bs.modal', function () {
            // do something…
            $scope.init();
        });

        $scope.myConfig = {
            plugins: ['remove_button'],
            create: false,
//            optgroupField: 'profil',
            labelField: 'name',

            valueField: 'username',
            searchField: ['name'],
            onChange: function (value) {
            }
//            render: {
//                optgroup_header: function (data, escape) {
//                    return '<div class="optgroup-header">' + escape(data.libelleProfil) + '</div>';
//                }
//            },
//            optgroups: profilesGrp

        };
//        ===============================================================================================

        $scope.indexSave = -1;

        $scope.saveIndexObject = function (index) {
            $scope.indexSave = index;
        };

        $scope.selectedClassification = null;
        $scope.openClassificationModal = function (ext) {
            $('#classification-modal-wkf').modal('show');
            $scope.selectedClassification = ext.id;
            $scope.claId = $scope.extensionList[$scope.indexSave].valeur;
        };
        $scope.loadClassificationTree = function () {
            $('#classifTree').jstree({
                checkbox: {
                    three_state: false
                },
                'plugins': ['search', 'types', 'checkbox'],
                "core": {
                    "multiple": false,
                    "animation": 0,
                    "check_callback": true,
                    "themes": {"stripes": true},
                    'data': {
                        'dataType': "json",
                        'url': function (node) {
                            var url = $('#marbre_noeud').attr('href');
                            return url + "api/groupe_extension/recherche-classification/" + $scope.selectedClassification;
                        },
                        'data': function (node) {
                            return {'id': node.id, 'cla': $scope.claId};
                        }
                    }
                },
                "types": {
                    "#": {
                        "max_children": 3,
                        "max_depth": 4,
                        "valid_children": ["default"]
                    },
                    "root": {
                        'icon': 'fa fa-folder',
                        "valid_children": ["default"]
                    },
                    'default': {
                        'icon': 'fa fa-folder'
                    },
                    'file': {
                        'icon': 'fa fa-file'
                    }
                }, "html_titles": true, "load_open": true
            });
//            $('#classifTree').jstree("refresh");
            $("#classifTree").on("select_node.jstree", function (evt, data) {
                if (data.node.id != null) {
                    $scope.classificationObject.idClassification = data.node.id;
                    $scope.classificationObject.libelleClassification = data.node.text;
                    $scope.setClassificationValue();
                }
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            });
            $("#classifTree").on("deselect_node.jstree", function (evt, data) {
                $scope.classificationObject = angular.copy($scope.classificationObjectMaster);
                $scope.setClassificationValue();
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            });
        };

        $scope.closeClassificationModal = function (ext) {
            $('#classification-modal-wkf').modal('hide');
        };

        $('#classification-modal-wkf').on('shown.bs.modal', function () {
//            $('#classifTree').jstree("refresh");
            $('#classifTree').jstree("destroy").empty();
            $scope.loadClassificationTree();
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        });

        $('#classification-modal-wkf').on('hide.bs.modal', function () {
//            $scope.indexSave = -1;
            $scope.selectedClassification = null;
            $("#classifTree").jstree("deselect_all");
            $('#classifTree').jstree("refresh");
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        });

        $scope.classificationObject = {idClassification: null, libelleClassification: null};
        $scope.classificationObjectMaster = {idClassification: null, libelleClassification: null};

        $scope.setClassificationValue = function () {
            if ($scope.indexSave != -1) {
                $scope.extensionList[$scope.indexSave].valeur = $scope.classificationObject.idClassification;
                $scope.extensionList[$scope.indexSave].libelleClassification = $scope.classificationObject.libelleClassification;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            } else {
                $scope.extensionList[$scope.indexSave].valeur = null;
                $scope.extensionList[$scope.indexSave].libelleClassification = "";
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }
        };

    }]);
