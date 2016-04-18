Ext.define('Testtwo.testtwo.shared.com.model.appinsight.health.NewwModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "cc",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "df",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});