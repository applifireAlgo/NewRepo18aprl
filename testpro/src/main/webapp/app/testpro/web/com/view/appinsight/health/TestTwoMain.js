Ext.define('Testpro.testpro.web.com.view.appinsight.health.TestTwoMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "TestTwoMainController",
     "restURL": "/TestTwo",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.appinsight.health.TestTwoModel", "Testpro.testpro.web.com.controller.appinsight.health.TestTwoMainController", "Testpro.testpro.shared.com.viewmodel.appinsight.health.TestTwoViewModel"],
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
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "TestTwo",
               "name": "TestTwoTreeContainer",
               "itemId": "TestTwoTreeContainer",
               "restURL": "/TestTwo",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "TestTwoTree",
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
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "text": "Filter",
                              "name": "filterButton",
                              "handler": "onFilterClick"
                         }]
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
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "TestTwo",
                    "title": "TestTwo",
                    "name": "TestTwo",
                    "itemId": "TestTwoForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "ttid",
                         "itemId": "ttid",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "ttid",
                         "margin": "5 5 5 5",
                         "fieldLabel": "ttid<font color='red'> *<\/font>",
                         "fieldId": "35D56D59-3FCC-4D28-B7B2-BD9FD6DFCFAA",
                         "hidden": true,
                         "value": "",
                         "bindable": "ttid"
                    }, {
                         "name": "tnm",
                         "itemId": "tnm",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "tnm",
                         "margin": "5 5 5 5",
                         "fieldLabel": "tnm<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "280CAA7E-C3ED-4DB5-B90C-C2D63331A8D0",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "tnm",
                         "columnWidth": 0.5
                    }, {
                         "name": "tno",
                         "itemId": "tno",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "tno",
                         "margin": "5 5 5 5",
                         "fieldLabel": "tno<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "33B2295C-5F27-45ED-B7CC-CB13F24E6226",
                         "minValue": "-2147483648",
                         "maxValue": "2147483647",
                         "bindable": "tno",
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
                         "fieldId": "6CF86E8A-17A1-4D4F-B847-C7A879463071",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 363,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 363,
                              "customId": 964
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 363,
                              "customId": 965,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": "0 5 0 5",
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 363,
                              "customId": 966,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }],
                         "defaults": {}
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "TestTwo",
                    "title": "Details Grid",
                    "name": "TestTwoGrid",
                    "itemId": "TestTwoGrid",
                    "restURL": "/TestTwo",
                    "store": [],
                    "bodyPadding": 10,
                    "columns": [{
                         "header": "ttid",
                         "dataIndex": "ttid",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "tnm",
                         "dataIndex": "tnm",
                         "flex": 1
                    }, {
                         "header": "tno",
                         "dataIndex": "tno",
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
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "TestTwo",
               "title": "TestTwo",
               "name": "TestTwo",
               "itemId": "TestTwoForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "ttid",
                    "itemId": "ttid",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "ttid",
                    "margin": "5 5 5 5",
                    "fieldLabel": "ttid<font color='red'> *<\/font>",
                    "fieldId": "35D56D59-3FCC-4D28-B7B2-BD9FD6DFCFAA",
                    "hidden": true,
                    "value": "",
                    "bindable": "ttid"
               }, {
                    "name": "tnm",
                    "itemId": "tnm",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "tnm",
                    "margin": "5 5 5 5",
                    "fieldLabel": "tnm<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "280CAA7E-C3ED-4DB5-B90C-C2D63331A8D0",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "tnm",
                    "columnWidth": 0.5
               }, {
                    "name": "tno",
                    "itemId": "tno",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "tno",
                    "margin": "5 5 5 5",
                    "fieldLabel": "tno<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "33B2295C-5F27-45ED-B7CC-CB13F24E6226",
                    "minValue": "-2147483648",
                    "maxValue": "2147483647",
                    "bindable": "tno",
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
                    "fieldId": "6CF86E8A-17A1-4D4F-B847-C7A879463071",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 363,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 363,
                         "customId": 964
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 363,
                         "customId": 965,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": "0 5 0 5",
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 363,
                         "customId": 966,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }],
                    "defaults": {}
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});