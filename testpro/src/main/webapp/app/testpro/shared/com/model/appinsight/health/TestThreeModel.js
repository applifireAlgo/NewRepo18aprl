Ext.define('Testpro.testpro.shared.com.model.appinsight.health.TestThreeModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "tttid",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "ttnm",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "nnno",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "gen",
          "reference": "Gender",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "Address",
          "reference": "AddressModel"
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});