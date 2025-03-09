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
App.controller('wCycleVieController', ['$rootScope', '$scope', 'GenericService', 'PropagationService', function ($rootScope, $scope, GenericService, PropagationService) {
        var baseUrl = appUrl + 'api/utilisateurs/';
        var usersUrl = baseUrl + 'getUsersFromProfils';
        var extensionUrl = appUrl + 'api/groupe_extension/find-extensions';
        var modeCommentaireUrl = appUrl + 'api/groupe_extension/find-mode-comment';

        var profilAutoUrl = appUrl + 'api/parameter/autorisation/wf/stepAction';
        var initWorkflowURL = appUrl + 'api/parametre/fragment/conf/workflow-cycle/init-workflow-object';
        var initUsersURL = appUrl + 'api/parametre/fragment/conf/workflow-cycle/find-users';
        var wfAvancerObjURL = appUrl + 'api/parametre/fragment/conf/workflow-cycle/executer-action';
        var wfAvancerObjProgrammeURL = appUrl + 'api/parameter/ref/budgetaire/programme/executer-action-programme';
        $scope.montant = 0;

        $scope.wfStepObjMaster = {"idobj": null, "idStep": null, libelleObjet: null, natureObjet: null,
            "comment": null, "action": null, "nextStep": null, "notifyUsers": false,
            selectedUsers: [], conceptMetier: null};
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

                $scope.objectParams.idObjet = param.idObjet;
                $scope.objectParams.natureObjet = param.natureObjet;
                $scope.objectParams.conceptMetier = param.conceptMetier;
                //Ajouter par Assima
                $scope.objectParams.idGroupeExtension = param.idGroupeExtension;
                jslog("$rootScope.idGroupeExtension: " + angular.toJson(param.idGroupeExtension));
                $scope.init();
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
        },
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



        $scope.getUsers = function () {
            GenericService.post(usersUrl, angular.toJson($scope.profiles))
                    .then(
                            function (data) {
//                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                $scope.userList = data.users;
                            },
                            function (errResponse) {
                            }
                    );
        };


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

            $scope.wfStepObj.action = angular.element("#OneAction").val();
            $scope.wfStepObj.conceptMetier = $scope.objectParams.conceptMetier;
            $scope.wfStepObj.natureObjet = $scope.objectParams.natureObjet;
            $scope.wfStepObj.codeFonction = $scope.codeFonction;

            if ($scope.wfStepObj.action !== null) {
                jslog("entree recherche user");
                $scope.selectWfAction($scope.wfStepObj.action);
            }



            $scope.initWorkflowParams();

        };

        $scope.usersByAction = function (action) {
            {
                GenericService.get(initUsersURL + "/" + action)
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
        $scope.initWorkflowParams = function () {
            jslog("initWorkflowParams wfStepObj: " + angular.toJson($scope.wfStepObj));

            $scope.workflowCycleParameter.concepMetier = $scope.wfStepObj.conceptMetier;
            $scope.workflowCycleParameter.idObjet = $scope.wfStepObj.idobj;
            $scope.workflowCycleParameter.codeFonction = $scope.wfStepObj.codeFonction;


            if ($scope.wfStepObj && $scope.wfStepObj.conceptMetier !== null && $scope.wfStepObj.idobj !== null) {
                jslog("initWorkflowParams workflowCycleParameter: " + angular.toJson($scope.workflowCycleParameter));
                GenericService.post(initWorkflowURL, angular.toJson($scope.workflowCycleParameter))
                        .then(
                                function (data) {
                                    jslog("initWorkflowParams data " + angular.toJson(data));
                                    $scope.workflowObjet = data;

                                    if (data.actions && data.actions.length === 1) {
                                        $scope.userList = data.utilisateursAutorises;
                                        $scope.actionSelected = data.actions[0];
                                        $scope.wfStepObj.action = $scope.actionSelected.id;
                                        $scope.userListGroupe();
                                    }
                                    /*
                                     * Aucune action permise
                                     */
                                    if (data.actions && data.actions.length === 0) {
//                                        $scope.msg = "Aucune action ne vous est permise à cet état!";
                                        $scope.noActionError = angular.element('#noActionError').val();
                                        jslog("$scope.noActionError : " + $scope.noActionError);
                                        GenericService.frontValidationMessages("WARNING", $scope.noActionError);
                                    }
                                },
                                function (errResponse) {
                                    jslog("initWorkflowURL errResponse : " + angular.toJson(errResponse));
                                }
                        );
            }
        };

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

        $scope.selectWfAction = function (action) {

            jslog("selectWfAction:  actionId : " + action);
            if (action) {

                angular.forEach($scope.workflowObjet.actions, function (val) {
                    if (val.id === action) {
                        $scope.actionSelected = val;
                        $scope.wfStepObj.action = $scope.actionSelected.id;
                        $scope.usersByAction($scope.wfStepObj.action);

                        //Ajouter par Assima
                        $scope.getIndicateurCommentaire(action);
                    }
                });


            }
        };
        /**
         * Ajouter par Assima
         */
        $scope.modeEtendu = false;
        $scope.getIndicateurCommentaire = function (action) {
            jslog(" ================================  FONCTION GET INDICATEUR COMMENTAIRE  :  =================================");
            GenericService.get(modeCommentaireUrl + "/" + action)
                    .then(
                            function (data) {
                                jslog(" ================================  TYPE DE MODE ETENDU COMMENTTAIRE  :  =================================" + angular.toJson(data.modeEtendu));
                                $scope.modeEtendu = data.modeEtendu;
                                if (data.modeEtendu == true) {
                                    if ($scope.objectParams.idGroupeExtension && $scope.objectParams.idGroupeExtension != null) {
                                        $scope.getExtension($scope.objectParams.idGroupeExtension);
                                    }
                                } else {
                                    $scope.extensionList = [];
                                }
                            },
                            function (errResponse) {
                            }
                    );

        };
        $scope.displayListeDeControleMultiple = function (ext) {
            var value = false;
            if (ext.typeDonneListDeControle && ext.typeDonneListDeControle != null && ext.typeDonnee == "M") {
                jslog("ENTREZ DANS LA FONCTION DE DISPLAY MULTIPLE");
                value = true;
            }
            return value;
        };
        $scope.displayListeDeControleSelect = function (ext) {
            var value = false;
            if (ext.typeDonneListDeControle && !ext.codeListDeControleIsOUI_NON  && ext.typeDonneListDeControle != null && ext.typeDonnee != "M") {
                value = true;
            }
            return value;
        };
           $scope.displayListeDeControleSwitch = function (ext) {
            var value = false;
            if (ext.typeDonneListDeControle && ext.codeListDeControleIsOUI_NON  && ext.typeDonneListDeControle != null && ext.typeDonnee != "M") {
                value = true;
            }
            return value;
        };
        $scope.addSelectedValue = function (ext) {
//            splice(0, 0, angular.copy($scope.tiersDepensePieceUse))
//            ext.valeurMultipleChecked.splice(0, 0, angular.copy(idCodif));
            jslog("LES ELEMENT DE L'EXTENSION ");
            jslog("LES ELEMENT DE L'EXTENSION "+ angular.toJson(ext) );
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
            $scope.workflowCycleParameter.idAction = (object.action) ? object.action : angular.element("#OneAction").val();
            $scope.workflowCycleParameter.commentaire = object.comment;
            $scope.workflowCycleParameter.users = $scope.wfStepObj.selectedUsers;
            //Ajouter par Assima DEBUT
            $scope.workflowCycleParameter.extensionsList = $scope.extensionList;
            //Ajouter par Assima FIN

            jslog("cycleVieController workflowCycleParameter  : " + angular.toJson($scope.workflowCycleParameter));

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

                                GenericService.frontValidationMessages("INFO", "Opération réussie");
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
                $scope.workflowCycleParameter.idAction = (object.action) ? object.action : angular.element("#OneAction").val();
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
            create: true,
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
    }]);
