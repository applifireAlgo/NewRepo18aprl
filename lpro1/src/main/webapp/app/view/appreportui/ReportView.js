Ext.define('Lpro1.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Lpro1.view.appreportui.ReportViewController',
	            'Lpro1.view.appreportui.datagrid.DataGridPanel',
	            'Lpro1.view.appreportui.datagrid.DataGridView',
	            'Lpro1.view.appreportui.querycriteria.QueryCriteriaView',
	            'Lpro1.view.appreportui.chart.ChartView',
	            'Lpro1.view.appreportui.datapoint.DataPointView',
	            'Lpro1.view.googlemaps.map.MapPanel',
	            'Lpro1.view.appreportui.chartpoint.ChartPointView'
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
