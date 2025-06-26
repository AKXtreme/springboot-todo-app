<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login Page</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(to right, #2c3e50, #3498db);
            font-family: 'Segoe UI', sans-serif;
            display: flex;
            height: 100vh;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
            width: 300px;
            text-align: center;
        }

        .login-container h2 {
            margin-bottom: 24px;
            color: #2c3e50;
        }

        .login-container input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .login-container button {
            width: 100%;
            padding: 12px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .login-container button:hover {
            background-color: #2980b9;
        }

        .login-container p {
            margin-top: 16px;
            font-size: 14px;
            color: #7f8c8d;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Welcome Back</h2>

    <p>${error}</p>
    <form action="login" method="post">
        <input type="text" name="username" placeholder="Username" required />
        <input type="password" name="password" placeholder="Password" required />
        <button type="submit">Log In</button>
    </form>

    <p>Forgot your password?</p>
</div>
</body>
</html>
