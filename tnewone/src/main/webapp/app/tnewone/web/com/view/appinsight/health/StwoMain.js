Ext.define('Tnewone.tnewone.web.com.view.appinsight.health.StwoMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "StwoMainController",
     "restURL": "/Stwo",
     "defaults": {
          "split": true
     },
     "requires": ["Tnewone.tnewone.shared.com.model.appinsight.health.StwoModel", "Tnewone.tnewone.web.com.controller.appinsight.health.StwoMainController", "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.GenderModel", "Tnewone.tnewone.shared.com.viewmodel.appinsight.health.StwoViewModel"],
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
               "displayName": "Stwo",
               "name": "StwoTreeContainer",
               "itemId": "StwoTreeContainer",
               "restURL": "/Stwo",
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
                    "itemId": "StwoTree",
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
                    "displayName": "Stwo",
                    "title": "Stwo",
                    "name": "Stwo",
                    "itemId": "StwoForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "stwoid",
                         "itemId": "stwoid",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Stwoid",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Stwoid<font color='red'> *<\/font>",
                         "fieldId": "5D744D2B-E4D5-43D7-8486-3FB69112F07C",
                         "hidden": true,
                         "value": "",
                         "bindable": "stwoid"
                    }, {
                         "name": "snmn",
                         "itemId": "snmn",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "NAme",
                         "margin": "5 5 5 5",
                         "fieldLabel": "NAme<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "AF11BB2F-E22A-4057-8FCF-8CFB30E23F8C",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "snmn",
                         "columnWidth": 0.5
                    }, {
                         "name": "sds",
                         "itemId": "sds",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "sdsd",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.GenderModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "sdsd<font color='red'> *<\/font>",
                         "fieldId": "D9A72100-6AA6-47BD-8B74-FE53F50271EA",
                         "restURL": "Gender",
                         "bindable": "sds",
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
                         "fieldId": "C90DF2D1-1DF1-42C2-A5E5-C2D6C1E12084",
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
                         "customId": 247,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 247,
                              "customId": 824
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 247,
                              "customId": 825,
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
                              "parentId": 247,
                              "customId": 826,
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
                    "displayName": "Stwo",
                    "title": "Details Grid",
                    "name": "StwoGrid",
                    "itemId": "StwoGrid",
                    "restURL": "/Stwo",
                    "store": [],
                    "bodyPadding": 10,
                    "columns": [{
                         "header": "Stwoid",
                         "dataIndex": "stwoid",
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
                         "header": "NAme",
                         "dataIndex": "snmn",
                         "flex": 1
                    }, {
                         "header": "sdsd",
                         "dataIndex": "sds",
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
               "displayName": "Stwo",
               "title": "Stwo",
               "name": "Stwo",
               "itemId": "StwoForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "stwoid",
                    "itemId": "stwoid",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Stwoid",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Stwoid<font color='red'> *<\/font>",
                    "fieldId": "5D744D2B-E4D5-43D7-8486-3FB69112F07C",
                    "hidden": true,
                    "value": "",
                    "bindable": "stwoid"
               }, {
                    "name": "snmn",
                    "itemId": "snmn",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "NAme",
                    "margin": "5 5 5 5",
                    "fieldLabel": "NAme<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "AF11BB2F-E22A-4057-8FCF-8CFB30E23F8C",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "snmn",
                    "columnWidth": 0.5
               }, {
                    "name": "sds",
                    "itemId": "sds",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "sdsd",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Tnewone.tnewone.shared.com.model.organizationboundedcontext.contacts.GenderModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "sdsd<font color='red'> *<\/font>",
                    "fieldId": "D9A72100-6AA6-47BD-8B74-FE53F50271EA",
                    "restURL": "Gender",
                    "bindable": "sds",
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
                    "fieldId": "C90DF2D1-1DF1-42C2-A5E5-C2D6C1E12084",
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
                    "customId": 247,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 247,
                         "customId": 824
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 247,
                         "customId": 825,
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
                         "parentId": 247,
                         "customId": 826,
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