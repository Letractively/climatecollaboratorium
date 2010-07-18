/* 
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 *
 * Init graphs 
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */

$(document).ready(function() {
line1 = [1, 4, 3, 2];
line2 = [7, 1, 2, 1];
line3 = [2, 7, 1, 3];
plot1 = $.jqplot('chart1', [line1, line2, line3], {
    legend: {
        show: true,
        location: 'nw',
        xoffset: 55
    },
    title: 'Bar Chart with Shadows',
    seriesDefaults: {
        renderer: $.jqplot.BarRenderer,
        rendererOptions: {
            barPadding: 10,
            barMargin: 10
        }
    },
    series: [{
        label: 'Profits'
    },
    {
        label: 'Expenses'
    },
    {
        label: 'Sales'
    }],
    axes: {
        xaxis: {
            renderer: $.jqplot.CategoryAxisRenderer,
            ticks: ['1st Qtr', '2nd Qtr', '3rd Qtr', '4th Qtr']
        },
        yaxis: {min:0}
    }
});
plot2 = $.jqplot('chart2', [line1, line2, line3], {
    legend: {
        show: true,
        location: 'nw',
        xoffset: 55
    },
    title: 'Bar Chart with Shadows',
    seriesDefaults: {
        renderer: $.jqplot.BarRenderer,
        rendererOptions: {
            barPadding: 10,
            barMargin: 10
        }
    },
    series: [{
        label: 'Profits'
    },
    {
        label: 'Expenses'
    },
    {
        label: 'Sales'
    }],
    axes: {
        xaxis: {
            renderer: $.jqplot.CategoryAxisRenderer,
            ticks: ['1st Qtr', '2nd Qtr', '3rd Qtr', '4th Qtr']
        },
        yaxis: {min:0}
    }
});
plot3 = $.jqplot('chart3', [line1, line2, line3], {
    legend: {
        show: true,
        location: 'nw',
        xoffset: 55
    },
    title: 'Bar Chart with Shadows',
    seriesDefaults: {
        renderer: $.jqplot.BarRenderer,
        rendererOptions: {
            barPadding: 10,
            barMargin: 10
        }
    },
    series: [{
        label: 'Profits'
    },
    {
        label: 'Expenses'
    },
    {
        label: 'Sales'
    }],
    axes: {
        xaxis: {
            renderer: $.jqplot.CategoryAxisRenderer,
            ticks: ['1st Qtr', '2nd Qtr', '3rd Qtr', '4th Qtr']
        },
        yaxis: {min:0}
    }
});
plot4 = $.jqplot('chart4', [line1, line2, line3], {
    legend: {
        show: true,
        location: 'nw',
        xoffset: 55
    },
    title: 'Bar Chart with Shadows',
    seriesDefaults: {
        renderer: $.jqplot.BarRenderer,
        rendererOptions: {
            barPadding: 10,
            barMargin: 10
        }
    },
    series: [{
        label: 'Profits'
    },
    {
        label: 'Expenses'
    },
    {
        label: 'Sales'
    }],
    axes: {
        xaxis: {
            renderer: $.jqplot.CategoryAxisRenderer,
            ticks: ['1st Qtr', '2nd Qtr', '3rd Qtr', '4th Qtr']
        },
        yaxis: {min:0}
    }
});
plot5 = $.jqplot('chart5', [line1, line2, line3], {
    legend: {
        show: true,
        location: 'nw',
        xoffset: 55
    },
    title: 'Bar Chart with Shadows',
    seriesDefaults: {
        renderer: $.jqplot.BarRenderer,
        rendererOptions: {
            barPadding: 10,
            barMargin: 10
        }
    },
    series: [{
        label: 'Profits'
    },
    {
        label: 'Expenses'
    },
    {
        label: 'Sales'
    }],
    axes: {
        xaxis: {
            renderer: $.jqplot.CategoryAxisRenderer,
            ticks: ['1st Qtr', '2nd Qtr', '3rd Qtr', '4th Qtr']
        },
        yaxis: {min:0}
    }
});
plot6 = $.jqplot('chart6', [line1, line2, line3], {
    legend: {
        show: true,
        location: 'nw',
        xoffset: 55
    },
    title: 'Bar Chart with Shadows',
    seriesDefaults: {
        renderer: $.jqplot.BarRenderer,
        rendererOptions: {
            barPadding: 10,
            barMargin: 10
        }
    },
    series: [{
        label: 'Profits'
    },
    {
        label: 'Expenses'
    },
    {
        label: 'Sales'
    }],
    axes: {
        xaxis: {
            renderer: $.jqplot.CategoryAxisRenderer,
            ticks: ['1st Qtr', '2nd Qtr', '3rd Qtr', '4th Qtr']
        },
        yaxis: {min:0}
    }
});
});