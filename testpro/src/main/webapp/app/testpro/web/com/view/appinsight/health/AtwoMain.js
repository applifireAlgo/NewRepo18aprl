Ext.define('Testpro.testpro.web.com.view.appinsight.health.AtwoMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "AtwoMainController",
     "restURL": "/Atwo",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.appinsight.health.AtwoModel", "Testpro.testpro.web.com.controller.appinsight.health.AtwoMainController", "Testpro.testpro.shared.com.model.organization.contactmanagement.GenderModel", "Testpro.testpro.shared.com.viewmodel.appinsight.health.AtwoViewModel"],
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
               "displayName": "Atwo",
               "name": "AtwoTreeContainer",
               "itemId": "AtwoTreeContainer",
               "restURL": "/Atwo",
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
                    "itemId": "AtwoTree",
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
                    "displayName": "Atwo",
                    "title": "Atwo",
                    "name": "Atwo",
                    "itemId": "AtwoForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "aaid",
                         "itemId": "aaid",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "aaid",
                         "margin": "5 5 5 5",
                         "fieldLabel": "aaid<font color='red'> *<\/font>",
                         "fieldId": "9C62400A-1413-48A4-B75E-EA929F5897AD",
                         "hidden": true,
                         "value": "",
                         "bindable": "aaid"
                    }, {
                         "name": "gfg",
                         "itemId": "gfg",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "fgf",
                         "margin": "5 5 5 5",
                         "fieldLabel": "fgf<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "2A6B131A-3C0D-46AF-99C1-43CB7119A0DC",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "gfg",
                         "columnWidth": 0.5
                    }, {
                         "name": "gen",
                         "itemId": "gen",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "gender",
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
                         "fieldLabel": "gender<font color='red'> *<\/font>",
                         "fieldId": "8A1E9968-8AA4-42E5-AC60-0C970543B8CC",
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
                         "fieldId": "16853888-DB9E-4201-9136-6A7E718EBB98",
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
                         "customId": 803,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 803,
                              "customId": 629
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 803,
                              "customId": 630,
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
                              "parentId": 803,
                              "customId": 631,
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
                    "displayName": "Atwo",
                    "title": "Details Grid",
                    "name": "AtwoGrid",
                    "itemId": "AtwoGrid",
                    "restURL": "/Atwo",
                    "store": [],
                    "bodyPadding": 10,
                    "columns": [{
                         "header": "aaid",
                         "dataIndex": "aaid",
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
                         "header": "fgf",
                         "dataIndex": "gfg",
                         "flex": 1
                    }, {
                         "header": "gender",
                         "dataIndex": "gen",
                         "renderer": "renderFormValue",
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
               "displayName": "Atwo",
               "title": "Atwo",
               "name": "Atwo",
               "itemId": "AtwoForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "aaid",
                    "itemId": "aaid",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "aaid",
                    "margin": "5 5 5 5",
                    "fieldLabel": "aaid<font color='red'> *<\/font>",
                    "fieldId": "9C62400A-1413-48A4-B75E-EA929F5897AD",
                    "hidden": true,
                    "value": "",
                    "bindable": "aaid"
               }, {
                    "name": "gfg",
                    "itemId": "gfg",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "fgf",
                    "margin": "5 5 5 5",
                    "fieldLabel": "fgf<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "2A6B131A-3C0D-46AF-99C1-43CB7119A0DC",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "gfg",
                    "columnWidth": 0.5
               }, {
                    "name": "gen",
                    "itemId": "gen",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "gender",
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
                    "fieldLabel": "gender<font color='red'> *<\/font>",
                    "fieldId": "8A1E9968-8AA4-42E5-AC60-0C970543B8CC",
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
                    "fieldId": "16853888-DB9E-4201-9136-6A7E718EBB98",
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
                    "customId": 803,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 803,
                         "customId": 629
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 803,
                         "customId": 630,
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
                         "parentId": 803,
                         "customId": 631,
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