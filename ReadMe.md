## For PDF Response:
### If you do not want to enforce PDF download on the API call, use:

```java
return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=paragraph.pdf")
        .body(new InputStreamResource(pdfStream));
```

```bash
curl -L 'http://localhost:8080/api/paragraph?format=pdf' -o paragraph.pdf
```
```bash
curl -L -OJ 'http://localhost:8080/api/paragraph?format=pdf'
```
### If you want to enforce PDF download on the API call, use:

```java
return ResponseEntity.ok()
.contentType(MediaType.APPLICATION_PDF)
.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=paragraph.pdf")
.body(new InputStreamResource(pdfStream));
```

```bash
curl -I 'http://localhost:8080/api/paragraph?format=pdf'
```

## For csv response 
### Direct download from browser:
http://localhost:8080/api/paragraph?format=csv

### Other ways, to get data:
curl -I 'http://localhost:8080/api/paragraph?format=csv'


### Direct download via curl command:
curl -L \
'http://localhost:8080/api/paragraph?format=csv' \
-o paragraph.csv

## For JSON Reponse:

curl -X 'GET' \
'http://localhost:8080/api/paragraph?format=json' \
-H 'accept: */*'