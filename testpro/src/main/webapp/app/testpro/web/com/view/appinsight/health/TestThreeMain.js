Ext.define('Testpro.testpro.web.com.view.appinsight.health.TestThreeMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "TestThreeMainController",
     "restURL": "/TestThree",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.appinsight.health.TestThreeModel", "Testpro.testpro.web.com.controller.appinsight.health.TestThreeMainController", "Testpro.testpro.shared.com.model.organization.contactmanagement.GenderModel", "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel", "Testpro.testpro.shared.com.viewmodel.appinsight.health.TestThreeViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "displayName": "TestThree",
               "name": "TestThreeTreeContainer",
               "itemId": "TestThreeTreeContainer",
               "margin": "5 0 5 5",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "useArrows": true,
                    "name": "entityTreePanel",
                    "title": "Browse",
                    "rootVisible": false,
                    "itemId": "TestThreeTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "xtype": "form",
                    "displayName": "TestThree",
                    "name": "TestThree",
                    "itemId": "TestThreeForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form0",
                         "customWidgetType": "vdCard",
                         "header": {
                              "hidden": true
                         },
                         "items": [{
                              "layout": "column",
                              "customWidgetType": "vdColumnLayout",
                              "header": {
                                   "hidden": true
                              },
                              "xtype": "panel",
                              "items": [{
                                   "name": "tttid",
                                   "itemId": "tttid",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "tttid",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "tttid<font color='red'> *<\/font>",
                                   "fieldId": "9BE540DF-CB3A-4213-99ED-9688534CFD65",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "tttid"
                              }, {
                                   "name": "ttnm",
                                   "itemId": "ttnm",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "ttnm",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "ttnm<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "F9196853-7B3D-4F46-B48B-E974C66D75B1",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "ttnm",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "nnno",
                                   "itemId": "nnno",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "nnno",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "nnno<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "3022A57A-543D-45CF-83E2-A95EC60BB7D3",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "nnno",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "gen",
                                   "itemId": "gen",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "gendr",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Testpro.testpro.shared.com.model.organization.contactmanagement.GenderModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "gendr<font color='red'> *<\/font>",
                                   "fieldId": "E751B7D8-DE07-4066-8F10-0D19D7198CEA",
                                   "restURL": "Gender",
                                   "bindable": "gen",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "versionId",
                                   "itemId": "versionId",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "versionId",
                                   "margin": "5 5 5 5",
                                   "value": "-1",
                                   "fieldLabel": "versionId",
                                   "fieldId": "D4D9A4CE-90D8-40B5-9F63-FA9E9BC607FE",
                                   "bindable": "versionId",
                                   "hidden": true
                              }, {
                                   "xtype": "combo",
                                   "name": "Address",
                                   "displayField": "primaryDisplay",
                                   "valueField": "primaryKey",
                                   "margin": 5,
                                   "bindable": "address.addressId",
                                   "typeAhead": true,
                                   "columnWidth": 0.5,
                                   "queryMode": "local",
                                   "minChars": 1,
                                   "fieldLabel": "Address<font color='red'> *<\/font>",
                                   "title": "Address",
                                   "itemId": "address",
                                   "store": {
                                        "data": [],
                                        "model": "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel"
                                   }
                              }]
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Get Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "layout": "card",
                    "defaults": {
                         "autoScroll": true
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "margin": 0,
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": "0 5 0 5",
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }],
                         "defaults": {
                              "margin": "0 5 0 5"
                         }
                    }],
                    "listeners": {},
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "TestThree",
                    "title": "Details Grid",
                    "name": "TestThreeGrid",
                    "itemId": "TestThreeGrid",
                    "store": [],
                    "bodyPadding": 10,
                    "requires": [],
                    "columns": [{
                         "header": "tttid",
                         "dataIndex": "tttid",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "ttnm",
                         "dataIndex": "ttnm",
                         "flex": 1
                    }, {
                         "header": "nnno",
                         "dataIndex": "nnno",
                         "flex": 1
                    }, {
                         "header": "gendr",
                         "dataIndex": "gen",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "width": 30,
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "xtype": "form",
               "displayName": "TestThree",
               "name": "TestThree",
               "itemId": "TestThreeForm",
               "bodyPadding": 10,
               "items": [{
                    "xtype": "form",
                    "itemId": "form0",
                    "customWidgetType": "vdCard",
                    "header": {
                         "hidden": true
                    },
                    "items": [{
                         "layout": "column",
                         "customWidgetType": "vdColumnLayout",
                         "header": {
                              "hidden": true
                         },
                         "xtype": "panel",
                         "items": [{
                              "name": "tttid",
                              "itemId": "tttid",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "tttid",
                              "margin": "5 5 5 5",
                              "fieldLabel": "tttid<font color='red'> *<\/font>",
                              "fieldId": "9BE540DF-CB3A-4213-99ED-9688534CFD65",
                              "hidden": true,
                              "value": "",
                              "bindable": "tttid"
                         }, {
                              "name": "ttnm",
                              "itemId": "ttnm",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "ttnm",
                              "margin": "5 5 5 5",
                              "fieldLabel": "ttnm<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "F9196853-7B3D-4F46-B48B-E974C66D75B1",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "ttnm",
                              "columnWidth": 0.5
                         }, {
                              "name": "nnno",
                              "itemId": "nnno",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "nnno",
                              "margin": "5 5 5 5",
                              "fieldLabel": "nnno<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "3022A57A-543D-45CF-83E2-A95EC60BB7D3",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "nnno",
                              "columnWidth": 0.5
                         }, {
                              "name": "gen",
                              "itemId": "gen",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "gendr",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Testpro.testpro.shared.com.model.organization.contactmanagement.GenderModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "gendr<font color='red'> *<\/font>",
                              "fieldId": "E751B7D8-DE07-4066-8F10-0D19D7198CEA",
                              "restURL": "Gender",
                              "bindable": "gen",
                              "columnWidth": 0.5
                         }, {
                              "name": "versionId",
                              "itemId": "versionId",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "versionId",
                              "margin": "5 5 5 5",
                              "value": "-1",
                              "fieldLabel": "versionId",
                              "fieldId": "D4D9A4CE-90D8-40B5-9F63-FA9E9BC607FE",
                              "bindable": "versionId",
                              "hidden": true
                         }, {
                              "xtype": "combo",
                              "name": "Address",
                              "displayField": "primaryDisplay",
                              "valueField": "primaryKey",
                              "margin": 5,
                              "bindable": "address.addressId",
                              "typeAhead": true,
                              "columnWidth": 0.5,
                              "queryMode": "local",
                              "minChars": 1,
                              "fieldLabel": "Address<font color='red'> *<\/font>",
                              "title": "Address",
                              "itemId": "address",
                              "store": {
                                   "data": [],
                                   "model": "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel"
                              }
                         }]
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Get Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "layout": "card",
               "defaults": {
                    "autoScroll": true
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "margin": 0,
                    "isDockedItem": true,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": "0 5 0 5",
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "listeners": {
                              "click": "resetForm"
                         }
                    }],
                    "defaults": {
                         "margin": "0 5 0 5"
                    }
               }],
               "listeners": {},
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});