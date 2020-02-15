$(document).ready(function() {
    $('#ticketType').on('change', function() {
        var ticketTitle = document.getElementById('ticketTitle');
        if (this.value === 'COMPLAINT') {
            console.log('complaint got');
            // ticketTitle.find('option').not(':first').remove();
            $('#ticketTitle option:not(:first)').remove();
            var complaintList = [
                'ATM – Link down', 'ATM – Not dispensing', 'ATM – Out of Service', 'Cash balance – unable to login to portal'
                , 'Cheque truncation – faulty cheque scanner', 'Cheque truncation – unable to truncate cheque', 'Clearing – Failing'
                , 'Core Banking Application down', 'Core Banking slow', 'Form M – unable to download form', 'File Server – unable to access a shared folder'
                , 'GSS Scanner – Scanner failing', 'Hardware – Faulty Monitor', 'Hardware – Faulty Printer', 'Hardware – Faulty Switch', 'Hardware – Laptop not booting'
                , 'Hardware – Laptop slow', 'Hardware – OS crashed', 'Hardware – Printer not printing', 'Hardware – Router not coming up', 'Leave Portal down'
                , 'Loan Portal – Portal not loading', 'Loan Portal – unable to login', 'Network – Link down', 'Network – slow link', 'Western Union down'
            ];
            for (var i = 0; i < complaintList.length; i++) {
                ticketTitle.options.add(new Option(complaintList[i], complaintList[i]));
            }
        } else if (this.value === 'REQUEST') {
            console.log('request got');
            $('#ticketTitle option:not(:first)').remove();
            var requestList = [
                'ATM – Install new OS on ATM', 'ATM – Setup new ATM', 'Cheque Truncation – Request to setup Cheque Truncation', 'Clearing – setup page'
                , 'File server – request to access shared folder', 'Hardware – Fix Printer Toner', 'Hardware – Install Laptop', 'Hardware – Install Printer', 'Hardware – Install Scanner'
                , 'Hardware – Setup Router', 'Leave Portal – create login details', 'Network – Configure Network Device', 'Network – Configure Network Port', 'Network – Request to setup network'
                , 'Outlook – configure outlook', 'Outlook – create personal folder', 'Outlook – create rules', 'Request to bring in a device', 'Request to install OS on Data Centre Device'
                , 'System Support – setup Users', 'Western Union – setup'
            ];
            for (var i = 0; i < requestList.length; i++) {
                ticketTitle.options.add(new Option(requestList[i], requestList[i]));
            }
        }
    })
});


//         function fetchTicketTitle() {
//
//     var ticketTitle = document.getElementById('ticketTitle');
//     var selectedTicketType = document.getElementById('ticketType').value;
//     console.log(selectedTicketType);
//     if(selectedTicketType === 'COMPLAINT'){
//         console.log('complaint got');
//         var opt = document.createElement("option");
//
//         // Assign text and value to Option object
//         opt.text = "Network – slow link";
//         opt.value = "Network – slow link";
//         var complaintList = [
//             'ATM – Link down','ATM – Not dispensing','ATM – Out of Service','Cash balance – unable to login to portal'
//             ,'Cheque truncation – faulty cheque scanner','Cheque truncation – unable to truncate cheque','Clearing – Failing'
//             ,'Core Banking Application down','Core Banking slow','Form M – unable to download form','File Server – unable to access a shared folder'
//             ,'GSS Scanner – Scanner failing','Hardware – Faulty Monitor','Hardware – Faulty Printer','Hardware – Faulty Switch','Hardware – Laptop not booting'
//             ,'Hardware – Laptop slow','Hardware – OS crashed','Hardware – Printer not printing','Hardware – Router not coming up','Leave Portal down'
//             ,'Loan Portal – Portal not loading','Loan Portal – unable to login','Network – Link down','Network – slow link','Western Union down'
//         ];
//         ticketTitle.find('option').not(':first').remove(); //remove all but first
//         for(var i = 0; i < complaintList.length; i++){
//             ticketTitle.options.add(new Option(complaintList[i],complaintList[i]));
//         }
//     }
//     else if(selectedTicketType === 'REQUEST') {
//         console.log('request got');
//         var opt = document.createElement("option");
//
//         // Assign text and value to Option object
//         opt.text = "Network – Configure Network Device";
//         opt.value = "Network – Configure Network Device";
//         var requestList = ['ATM – Install new OS on ATM','ATM – Setup new ATM','Cheque Truncation – Request to setup Cheque Truncation','Clearing – setup page'
//             ,'File server – request to access shared folder','Hardware – Fix Printer Toner','Hardware – Install Laptop','Hardware – Install Printer','Hardware – Install Scanner'
//             ,'Hardware – Setup Router','Leave Portal – create login details','Network – Configure Network Device','Network – Configure Network Port','Network – Request to setup network'
//             ,'Outlook – configure outlook','Outlook – create personal folder','Outlook – create rules','Request to bring in a device','Request to install OS on Data Centre Device'
//             ,'System Support – setup Users','Western Union – setup'
//         ];
//         ticketTitle.options.not(':first').remove(); //remove all but first
//         for(var i = 0; i < requestList.length; i++){
//             ticketTitle.options.add(new Option(requestList[i],requestList[i]));
//         }
//     }
// }