<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h2>Welcome to the Home Page</h2>
<p>Here is your profile information:</p>
<ul>
    <li>Email: ${user.username}</li>
    <li>Roles: ${user.authorities}</li>
</ul>
<a href="/logout" onclick="event.preventDefault(); logout();">Logout</a>

<script>
    function logout() {
        fetch('/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    localStorage.removeItem('jwtToken');
                    window.location.href = '/login';
                } else {
                    alert('Logout failed');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
</script>
</body>
</html>
