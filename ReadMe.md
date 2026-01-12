If you dont want to enforce downlaod pdf on api call m then use:
return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=paragraph.pdf")
//                    .body(new InputStreamResource(pdfStream));


curl -L \
'http://localhost:8080/api/paragraph?format=pdf' \
-o paragraph.pdf

or 

curl -L -OJ \
'http://localhost:8080/api/paragraph?format=pdf'


If you want to enforce download pdf on api call :
return ResponseEntity.ok()
.contentType(MediaType.APPLICATION_PDF)
.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=paragraph.pdf")
.body(new InputStreamResource(pdfStream));

curl -I 'http://localhost:8080/api/paragraph?format=pdf'
