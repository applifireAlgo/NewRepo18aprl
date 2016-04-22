Ext.define('Tnewone.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Tnewone.view.appreportui.ReportViewController',
	            'Tnewone.view.appreportui.datagrid.DataGridPanel',
	            'Tnewone.view.appreportui.datagrid.DataGridView',
	            'Tnewone.view.appreportui.querycriteria.QueryCriteriaView',
	            'Tnewone.view.appreportui.chart.ChartView',
	            'Tnewone.view.appreportui.datapoint.DataPointView',
	            'Tnewone.view.googlemaps.map.MapPanel',
	            'Tnewone.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData'
	}
});
