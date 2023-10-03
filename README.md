Fullstack application allowing code sharing between developers

Request exchange is done through RestAPI. The following requests are available:

1) GET /code/new - sends HTML page with fields for code input, limiting the number of views, and limiting the lifetime (number of seconds). If you want to create a code without restrictions, you should enter 0 in the field for restrictions. If restrictions have been entered, the code will be deleted from the database after they expire.

2) POST /api/code/new - sends data to the server. The code and restrictions are written to the database, the response sends the UUID code by which the code fragment sent to the server will be available.

3) GET /api/code/{UUID} and /code/{UUID} - sends JSON or HTML page with code fragment.

4) GET /api/code/latest and /code/latest - sends JSON array or HTML page with 10 latest code fragments, excluding fragments with restrictions.

Thymeleaf was used to embed templates in HTML
PostgreSQL is used as a database