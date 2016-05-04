Ext.define('Testpro.testpro.web.com.view.appinsight.health.ParentMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "ParentMainController",
     "restURL": "/Parent",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.appinsight.health.ParentModel", "Testpro.testpro.web.com.controller.appinsight.health.ParentMainController", "Testpro.view.fw.component.Grids", "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel", "Testpro.testpro.shared.com.viewmodel.appinsight.health.ParentViewModel"],
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
               "displayName": "Parent",
               "name": "ParentTreeContainer",
               "itemId": "ParentTreeContainer",
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
                    "itemId": "ParentTree",
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
                    "displayName": "Parent",
                    "name": "Parent",
                    "itemId": "ParentForm",
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
                                   "name": "pid",
                                   "itemId": "pid",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "pid",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "pid<font color='red'> *<\/font>",
                                   "fieldId": "F9F2864F-A261-4492-8B94-DFA5B1416697",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "pid"
                              }, {
                                   "name": "pName",
                                   "itemId": "pName",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "pName",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "pName<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "8EBAE5AA-1A95-4104-8E58-D84FE3F35EF3",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "pName",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "age",
                                   "itemId": "age",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Age",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Age<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "D73D8438-6E1D-4401-AE30-5007D0CE2F0B",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "age",
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
                                   "fieldId": "BB198C09-101A-4EA4-BBEF-FA5ACD0A10E8",
                                   "bindable": "versionId",
                                   "hidden": true
                              }]
                         }]
                    }, {
                         "xtype": "form",
                         "displayName": "Child",
                         "title": "Child",
                         "name": "Child",
                         "itemId": "ChildForm",
                         "bodyPadding": 10,
                         "items": [{
                              "xtype": "form",
                              "itemId": "form1",
                              "customWidgetType": "vdAnchorLayout",
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
                                        "name": "cid",
                                        "itemId": "cid",
                                        "xtype": "textfield",
                                        "customWidgetType": "vdTextfield",
                                        "displayName": "cid",
                                        "margin": "5 5 5 5",
                                        "fieldLabel": "cid<font color='red'> *<\/font>",
                                        "fieldId": "C2DE3580-2FF9-42A5-AA6E-492FAAC2A6FF",
                                        "hidden": true,
                                        "value": "",
                                        "bindable": "child.cid"
                                   }, {
                                        "name": "cName",
                                        "itemId": "cName",
                                        "xtype": "textfield",
                                        "customWidgetType": "vdTextfield",
                                        "displayName": "cName",
                                        "margin": "5 5 5 5",
                                        "fieldLabel": "cName<font color='red'> *<\/font>",
                                        "allowBlank": false,
                                        "fieldId": "77745F48-CD09-43D8-A667-0F7004033A46",
                                        "minLength": "1",
                                        "maxLength": "256",
                                        "bindable": "child.cName",
                                        "columnWidth": 0.5
                                   }, {
                                        "name": "cage",
                                        "itemId": "cage",
                                        "xtype": "numberfield",
                                        "customWidgetType": "vdNumberfield",
                                        "displayName": "Age",
                                        "margin": "5 5 5 5",
                                        "fieldLabel": "Age<font color='red'> *<\/font>",
                                        "allowBlank": false,
                                        "fieldId": "6C7EBCCD-BBC8-4719-AC32-E4CEA5805BE2",
                                        "minValue": "-2147483648",
                                        "maxValue": "2147483647",
                                        "bindable": "child.cage",
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
                                        "fieldId": "AC148254-0DDA-4ACA-905F-91F6E7384E9A",
                                        "bindable": "child.versionId",
                                        "hidden": true
                                   }, {
                                        "xtype": "combo",
                                        "name": "Address",
                                        "displayField": "primaryDisplay",
                                        "valueField": "primaryKey",
                                        "margin": 5,
                                        "bindable": "child.address.addressId",
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
                         }, {
                              "columnWidth": 1,
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "maxWidth": 110,
                              "text": "Add Child",
                              "handler": "addChild"
                         }, {
                              "xtype": "grids",
                              "customWidgetType": "vdGrid",
                              "title": "Child",
                              "columnWidth": 1,
                              "itemId": "ChildGrid",
                              "fieldLabel": "List",
                              "bindLevel": "child",
                              "bindable": "child",
                              "listeners": {
                                   "select": "onChildGridItemClick"
                              },
                              "store": {
                                   "fields": [],
                                   "data": []
                              },
                              "columns": [{
                                   "header": "cid",
                                   "text": "cid",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "cid",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "cName",
                                   "text": "cName",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "cName",
                                   "flex": 1
                              }, {
                                   "header": "Age",
                                   "text": "Age",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "cage",
                                   "flex": 1
                              }, {
                                   "header": "createdBy",
                                   "text": "createdBy",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "createdBy",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "createdDate",
                                   "text": "createdDate",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "createdDate",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "updatedBy",
                                   "text": "updatedBy",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "updatedBy",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "updatedDate",
                                   "text": "updatedDate",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "updatedDate",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "versionId",
                                   "text": "versionId",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "versionId",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "activeStatus",
                                   "text": "activeStatus",
                                   "customWidgetType": "vdGridColumn",
                                   "dataIndex": "activeStatus",
                                   "hidden": true,
                                   "flex": 1
                              }, {
                                   "header": "txnAccessCode",
                                   "text": "txnAccessCode",
                                   "customWidgetType": "vdGridColumn",
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
                                        "handler": "onDeleteActionColumnClick"
                                   }]
                              }]
                         }],
                         "customWidgetType": "vdCard"
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
                    }, {
                         "xtype": "toolbar",
                         "customWidgetType": "vdTBar",
                         "defaults": {
                              "margin": "0 5 0 5"
                         },
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "itemId": "cardPrev",
                              "text": "&laquo; Previous",
                              "handler": "showPreviousCard",
                              "disabled": true,
                              "margin": "0 5 0 5"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "itemId": "cardNext",
                              "text": "Next &raquo;",
                              "handler": "showNextCard",
                              "margin": "0 5 0 5"
                         }]
                    }],
                    "listeners": {},
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "Parent",
                    "title": "Details Grid",
                    "name": "ParentGrid",
                    "itemId": "ParentGrid",
                    "store": [],
                    "bodyPadding": 10,
                    "requires": [],
                    "columns": [{
                         "header": "pid",
                         "dataIndex": "pid",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "pName",
                         "dataIndex": "pName",
                         "flex": 1
                    }, {
                         "header": "Age",
                         "dataIndex": "age",
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
               "displayName": "Parent",
               "name": "Parent",
               "itemId": "ParentForm",
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
                              "name": "pid",
                              "itemId": "pid",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "pid",
                              "margin": "5 5 5 5",
                              "fieldLabel": "pid<font color='red'> *<\/font>",
                              "fieldId": "F9F2864F-A261-4492-8B94-DFA5B1416697",
                              "hidden": true,
                              "value": "",
                              "bindable": "pid"
                         }, {
                              "name": "pName",
                              "itemId": "pName",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "pName",
                              "margin": "5 5 5 5",
                              "fieldLabel": "pName<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "8EBAE5AA-1A95-4104-8E58-D84FE3F35EF3",
                              "minLength": "1",
                              "maxLength": "256",
                              "bindable": "pName",
                              "columnWidth": 0.5
                         }, {
                              "name": "age",
                              "itemId": "age",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Age",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Age<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "D73D8438-6E1D-4401-AE30-5007D0CE2F0B",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "bindable": "age",
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
                              "fieldId": "BB198C09-101A-4EA4-BBEF-FA5ACD0A10E8",
                              "bindable": "versionId",
                              "hidden": true
                         }]
                    }]
               }, {
                    "xtype": "form",
                    "displayName": "Child",
                    "title": "Child",
                    "name": "Child",
                    "itemId": "ChildForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form1",
                         "customWidgetType": "vdAnchorLayout",
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
                                   "name": "cid",
                                   "itemId": "cid",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "cid",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "cid<font color='red'> *<\/font>",
                                   "fieldId": "C2DE3580-2FF9-42A5-AA6E-492FAAC2A6FF",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "child.cid"
                              }, {
                                   "name": "cName",
                                   "itemId": "cName",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "cName",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "cName<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "77745F48-CD09-43D8-A667-0F7004033A46",
                                   "minLength": "1",
                                   "maxLength": "256",
                                   "bindable": "child.cName",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "cage",
                                   "itemId": "cage",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Age",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Age<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "6C7EBCCD-BBC8-4719-AC32-E4CEA5805BE2",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "child.cage",
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
                                   "fieldId": "AC148254-0DDA-4ACA-905F-91F6E7384E9A",
                                   "bindable": "child.versionId",
                                   "hidden": true
                              }, {
                                   "xtype": "combo",
                                   "name": "Address",
                                   "displayField": "primaryDisplay",
                                   "valueField": "primaryKey",
                                   "margin": 5,
                                   "bindable": "child.address.addressId",
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
                    }, {
                         "columnWidth": 1,
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "maxWidth": 110,
                         "text": "Add Child",
                         "handler": "addChild"
                    }, {
                         "xtype": "grids",
                         "customWidgetType": "vdGrid",
                         "title": "Child",
                         "columnWidth": 1,
                         "itemId": "ChildGrid",
                         "fieldLabel": "List",
                         "bindLevel": "child",
                         "bindable": "child",
                         "listeners": {
                              "select": "onChildGridItemClick"
                         },
                         "store": {
                              "fields": [],
                              "data": []
                         },
                         "columns": [{
                              "header": "cid",
                              "text": "cid",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "cid",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "cName",
                              "text": "cName",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "cName",
                              "flex": 1
                         }, {
                              "header": "Age",
                              "text": "Age",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "cage",
                              "flex": 1
                         }, {
                              "header": "createdBy",
                              "text": "createdBy",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "createdBy",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "createdDate",
                              "text": "createdDate",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "createdDate",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "updatedBy",
                              "text": "updatedBy",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "updatedBy",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "updatedDate",
                              "text": "updatedDate",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "updatedDate",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "versionId",
                              "text": "versionId",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "versionId",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "activeStatus",
                              "text": "activeStatus",
                              "customWidgetType": "vdGridColumn",
                              "dataIndex": "activeStatus",
                              "hidden": true,
                              "flex": 1
                         }, {
                              "header": "txnAccessCode",
                              "text": "txnAccessCode",
                              "customWidgetType": "vdGridColumn",
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
                                   "handler": "onDeleteActionColumnClick"
                              }]
                         }]
                    }],
                    "customWidgetType": "vdCard"
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
               }, {
                    "xtype": "toolbar",
                    "customWidgetType": "vdTBar",
                    "defaults": {
                         "margin": "0 5 0 5"
                    },
                    "isDockedItem": true,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "itemId": "cardPrev",
                         "text": "&laquo; Previous",
                         "handler": "showPreviousCard",
                         "disabled": true,
                         "margin": "0 5 0 5"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "itemId": "cardNext",
                         "text": "Next &raquo;",
                         "handler": "showNextCard",
                         "margin": "0 5 0 5"
                    }]
               }],
               "listeners": {},
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});