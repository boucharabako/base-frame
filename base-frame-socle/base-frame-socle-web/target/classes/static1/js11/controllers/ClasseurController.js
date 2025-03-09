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
App.controller('ClasseurController', ['$rootScope', '$scope', '$timeout', 'GenericService', 'PropagationService', function ($rootScope, $scope, $timeout, GenericService, PropagationService) {
        var baseUrl = appUrl + 'api/parametre/conf/classeur/';
        var findCodification = baseUrl + 'find-codification-classeurs';
        var saveCodificaion = baseUrl + 'save-codification';
        var saveClasseur = baseUrl + 'save-classeur';
        var deleteClasseur = baseUrl + 'delete-classeur';
        var deleteCodification = baseUrl + 'delete-codif';
        var statutEnSaisieUrl = baseUrl + 'find-statut-en-saissie';
        var typeOperation = $('#classeur-type-operation').val();
        var domaineID = $('#classeur-domaine-id').val();
        var porteeID = $('#classeur-domaine-id').val();
        var typeOperation = $('#classeur-portee-id').val();

        var PERSIST_TYPE_UPDATE = "up";
        var PERSIST_TYPE_INSERT = "in";

        var NATURE_ADD_ROOT = "root";
        var NATURE_ADD_CHILD = "child";
        var NATURE_UPDATE = "update";


        $scope.domaines = [];
        $scope.classeurs = [];
        $scope.portees = [];
        $scope.statutDto = null;

        $scope.ignoreChanges = false;
        $scope.ignoreChanges2 = false;
        $scope.node = null;

        $scope.selectedClasseur = {code: null, libelle: null, parent: null, getParent: function () {

                return $scope.treeJsOp.getOne(parent);

            }};

        $scope.codificationEdited = {editable:true,code: null, libelle: null, porte: $('#classeur-portee-id').val() ? $('#classeur-portee-id').val() : null,
            domaine: $('#classeur-domaine-id').val() ? $('#classeur-domaine-id').val() : null, statutDto: null};
        $scope.codificationModel = {editable:true,code: null, libelle: null, porte: $('#classeur-portee-id').val() ? $('#classeur-portee-id').val() : null,
            domaine: $('#classeur-domaine-id').val() ? $('#classeur-domaine-id').val() : null, statutDto: null};

        console.log($scope.codificationModel);
        $scope.getDomaines = function () {
            GenericService.get(appUrl + "api/utils/codification/DOMAINES")
                    .then(
                            function (data) {
//                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                $scope.domaines = data;
                            },
                            function (errResponse) {
                            }
                    );
        };
        $scope.getDomaines();
        $scope.getPortees = function () {
            GenericService.get(appUrl + "api/utils/codification/PORTEE")
                    .then(
                            function (data) {
//                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                $scope.portees = data;
                            },
                            function (errResponse) {
                            }
                    );
        };
        $scope.getPortees();
        $scope.getStatutEnSaisie = function () {
            GenericService.get(statutEnSaisieUrl)
                    .then(
                            function (data) {
//                                jslog("cycleVieController getUsers  : " + angular.toJson(data));
                                $scope.statutDto = data;
                                $scope.codificationEdited.statutDto = data;
                                $scope.codificationModel.statutDto = data;
                            },
                            function (errResponse) {
                            }
                    );
        };
        $scope.getStatutEnSaisie();
        $scope.getCodification = function () {
            var codificationId = $('#classeur-code').val();
            if (codificationId) {
                GenericService.get(findCodification + "?code=" + codificationId)
                        .then(
                                function (data) {
                                    console.log(data)
                                    $scope.statutDto = data;
                                    $scope.classeurs = data.classeurs;


                                    $scope.codificationEdited.code = data.code;
                                    $scope.codificationEdited.libelle = data.libelle;
                                    $scope.codificationEdited.porte = data.porte;
                                    $scope.codificationEdited.domaine = data.domaine;
                                    $scope.codificationEdited.editable = data.editable;
                                    $scope.codificationEdited.statutDto = data.statutDto;
                                    
                                    if($('#classeur-detailAtr').val()){
                                        $scope.codificationEdited.editable=false;
                                    }

                                    $scope.treeJsOp.addAll(data.classeurs);
                                    $scope.ignoreChanges = true;
                                    $scope.ignoreChanges2 = true;

                                },
                                function (errResponse) {
                                }
                        );

            }

        };
        $scope.getCodification();
        $scope.saveCodofication = function () {

            GenericService.post(saveCodificaion + "?t=" + typeOperation, $scope.codificationEdited)
                    .then(
                            function (data) {
                                if (typeOperation === PERSIST_TYPE_INSERT) {
                                    typeOperation = PERSIST_TYPE_UPDATE;
                                }
                                $scope.ignoreChanges = true;
                                $scope.ignoreChanges2 = true;
                            },
                            function (errResponse) {
                            }
                    );



        };
        $scope.saveClasseur = function (nature) {

            if (nature === NATURE_ADD_CHILD && $scope.node !== null) {

                $scope.selectedClasseur.parent = $scope.node.id;
                ;
            }
            $scope.selectedClasseur.codification = $scope.codificationEdited.code;
            console.log($scope.selectedClasseur);
            GenericService.post(saveClasseur, $scope.selectedClasseur)
                    .then(
                            function (data) {
                                $scope.selectedClasseur.id = data.id;
                                if (nature === NATURE_UPDATE) {
                                    $scope.treeJsOp.update($scope.selectedClasseur);

                                } else {
                                    $scope.treeJsOp.add($scope.selectedClasseur);

                                }
                                $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
                            },
                            function (errResponse) {
                            }
                    );



        };
        $scope.deleteClasseur = function () {
            showConfirmation("supprimer", function () {
                GenericService.post(deleteClasseur, {id: $scope.node.id})
                        .then(
                                function (data) {
                                    $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
                                    $scope.treeJsOp.data = [];
                                    $scope.treeJsOp.addAll(data.classeurs);
                                    $scope.node=null;
                                },
                                function (errResponse) {
                                }
                        );

            }, (" Cette action entraînera la suppression  de toutes les feuilles. "));

        };

        $scope.resetCodif = function () {
            $scope.treeJsOp.data = [];

            $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
            $scope.node = null;
            $scope.treeConfig.version++;
            angular.copy($scope.codificationModel, $scope.codificationEdited);
            typeOperation = PERSIST_TYPE_INSERT;
            $scope.ignoreChanges2=false;
        };
        $scope.deleteCodification = function () {
            showConfirmation("supprimer", function () {
                console.log({codification: $scope.codificationEdited.code});
                GenericService.post(deleteCodification, {codification: $scope.codificationEdited.code})
                        .then(
                                function (data) {
                                    $scope.treeJsOp.data = [];

                                    $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
                                    $scope.node = null;
                                    $scope.treeConfig.version++;
                                    angular.copy($scope.codificationModel, $scope.codificationEdited);
                                    typeOperation = PERSIST_TYPE_INSERT;
                                                                        $scope.resetCodif();


                                },
                                function (errResponse) {
                                }
                        );
            }, (" Cette action entraînera la suppression  de toutes les feuilles. "));


        };
        $scope.init = function () {
            $scope.treeJsOp.data = [];

            $scope.selectedClasseur = {code: null, id: null, libelle: null, parent: null};
            angular.copy($scope.codificationModel, $scope.codificationEdited);
        };

        $scope.treeJsOp = {
            data: [],
            data2: [],
            add: function (data) {
                if ($scope.node && $scope.node !== null) {
                    var p = $scope.node.id;
                    for (k = 0; k < $scope.treeJsOp.data.length; k++) {

                        if ($scope.node.id === $scope.treeJsOp.data[k].id) {
                            $scope.treeJsOp.data[k].state = {opened: true, selected: true};

                        } else {
                            $scope.treeJsOp.data[k].state = {selected: false};
                        }

                    }

                }
                this.data.push({id: data.id, text: data.libelle, parent: data.parent !== null ? data.parent : '#', code: data.code});


                $scope.ignoreChanges = true;
                $scope.treeConfig.version++;
            },
            update: function (data) {
                if ($scope.node && $scope.node !== null) {
                    var p = $scope.node.id;
                    for (k = 0; k < $scope.treeJsOp.data.length; k++) {

                        if ($scope.node.id === $scope.treeJsOp.data[k].id) {
                            $scope.treeJsOp.data[k].state = {opened: true, selected: true};
                            $scope.treeJsOp.data[k].text = data.libelle;
                            $scope.treeJsOp.data[k].code = data.code;
                            $scope.node.text = data.libelle;
                            $scope.node.code = data.code;
                            $scope.node.edited = false;
                            ;

                        } else {
                            $scope.treeJsOp.data[k].state = {selected: false};
                        }

                    }

                }


                $scope.ignoreChanges = true;
                $scope.treeConfig.version++;
            },
            addAll: function (classeurs) {
                angular.forEach(classeurs, function (val, key) {
                    $scope.treeJsOp.data.push({id: val.id, text: val.libelle, code: val.code,
                        parent: val.parent !== null ? val.parent : '#', state: {opened: false}});


                });


                if ($scope.node !== null && $scope.node.parent !== null && $scope.node.parent !== "#") {
                    var pr = $scope.node.parent;
                    for (k = 0; k < $scope.treeJsOp.data.length; k++) {
                        console.log("++++++++++++++" + $scope.treeJsOp.data[k]);
                        if (pr === $scope.treeJsOp.data[k].id) {
                            console.log($scope.treeJsOp.data[k]);
                            $scope.treeJsOp.data[k].state = {opened: true, selected: false};
                        } else {
                            $scope.treeJsOp.data[k].state = {selected: false};
                        }

                    }
                    $scope.node = null;
                }
                                    $scope.treeConfig.version++;

                console.log($scope.treeJsOp.data);
            },
            remoove: function (id) {
                var item;
                angular.forEach(this.data, function (val, key) {
                    if (val.id === id) {
                        item = val;
                    }
                });
                this.data.splice(this.data.indexOf(item), 1);
                $scope.ignoreChanges = true;
                $scope.treeConfig.version++;
            },
            getOne: function (id) {
                if (!id || id === null) {
                    return "";
                }
                var item;
                angular.forEach(this.data, function (val, key) {
                    if (val.id === id) {
                        item = val;
                    }
                });

                return item ? item.text : "";
            }
        };

        $scope.treeConfig = {
            core: {
                "multiple": false,
                "animation": 0,
                "check_callback": true,
                "themes": {"stripes": true},
            },
            "types": {
                "#": {
                    "max_children": 100,
                    "max_depth": 100,
                    "valid_children": ["default"],
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
            }, "html_titles": true, "load_open": true,
            "plugins": [
                "search", "types", "checkbox"
            ],
            'checkbox': {
                'deselect_all': true,
                'three_state': false
            },
            version: 1

        };



        angular.copy($scope.originalData, $scope.treeJsOp.data);

        angular.copy($scope.treeJsOp.data2, $scope.treeJsOp.data);

        $scope.applyModelChanges = function () {
            return !$scope.ignoreChanges;
        };

        $scope.selectNode = function (event, data) {

            $scope.node = data.node;
            $scope.node.code = $scope.node.original.code;
            console.info(data.node);

            $scope.node.edited = false;


        };
        $scope.deselectNode = function (event, data) {
            console.info(data.node);
            $scope.node = null;



        };
        $scope.openNode = function (event, data) {
            console.info(data.node);



        };



        $scope.editNode = function () {
            if ($scope.node != null) {
                $scope.selectedClasseur = {id: $scope.node.id, code: $scope.node.code, libelle: $scope.node.text, parent: $scope.node.parent};
                $scope.node.edited = true;
            }
        }


    }]);
