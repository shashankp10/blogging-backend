 Tech-Stack used :
        - Springboot
        - MySQL
        - JWT of springboot 3.0 Version

    End-points :
        Register a new user : http://localhost:8080/api/v1/register/user          [POST Request]
        Login as user : http://localhost:8080/api/v1/login                        [POST Request]

    Secured End-points required JWT token to access
        Crate a new blog : http://localhost:8080/api/v1/blog/post                 [POST Request]
        Get all blog : http://localhost:8080/api/v1/blog/                         [GET Request]
        Get all blog a userId : http://localhost:8080/api/v1/blog/user/{blogId}   [GET Request]
        Get blog by Id : http://localhost:8080/api/v1/blog/{blogId}               [GET Request]
        Update blog by Id : http://localhost:8080/api/v1/blog/{blogId}            [PUT Request]
        Delete blog by Id : http://localhost:8080/api/v1/blog/{blogId}            [DELETE Request]
