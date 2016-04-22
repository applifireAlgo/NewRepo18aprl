Ext.define('Tnewone.view.databrowsercalendar.DBCalendar', {
	extend : 'Tnewone.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Tnewone.view.databrowsercalendar.DBCalendarController',
	             'Tnewone.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}
});
