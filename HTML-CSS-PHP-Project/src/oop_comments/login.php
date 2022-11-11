<?php
  session_start();
  include "kozos.php";              
  $fiokok = loadUsers("users.txt"); 

  $uzenet = ""; 

  // űrlapfeldolgozás

  if (isset($_POST["login"])) {
    if (!isset($_POST["felhasznalonev"]) || trim($_POST["felhasznalonev"]) === "" || !isset($_POST["jelszo"]) || trim($_POST["jelszo"]) === "") {
      $uzenet = "<strong>Hiba:</strong> Adj meg minden adatot!";
    } else {
      $felhasznalonev = $_POST["felhasznalonev"];
      $jelszo = $_POST["jelszo"];

      $uzenet = "Sikertelen belépés! A belépési adatok nem megfelelők!";

      foreach ($fiokok as $fiok) {
        if ($fiok["felhasznalonev"] === $felhasznalonev && password_verify($jelszo, $fiok["jelszo"])) { // sikeres bejelentkezés
          $uzenet = "Sikeres belépés!";
          $_SESSION["user"] = $fiok;           // a "user" nevű munkamenet-változó a bejelentkezett felhasználót reprezentáló tömböt fogja tárolni
          header("Location: index.php");       // átirányítás az index.php oldalra
        }
      }
    }
  }
?>

<!DOCTYPE html>
<html lang="hu">
  <head>
    <title>Bejelentkezés</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/style.css"/>
  </head>
  <body>
    <main>
      <!-- Fejléc -->

      <header>
        <h1>Az én klassz weboldalam</h1>
      </header>

      <!-- Navigációs Menü -->

      <nav>
        <ul>
          <li><a href="index.php">Főoldal</a></li>
          <?php if (isset($_SESSION["user"])) { ?>
            <li><a href="profile.php">Profilom</a></li>
            <li><a href="logout.php">Kijelentkezés</a></li>
          <?php } else { ?>
            <li class="active"><a href="login.php">Bejelentkezés</a></li>
            <li><a href="signup.php">Regisztráció</a></li>
          <?php } ?>
        </ul>
      </nav>

      <!-- Az oldal tartalma -->

      <section id="content">
        <h2>Bejelentkezés</h2>
        <hr/>

        <!-- Bejelentkezés űrlap -->

        <form class="login" action="login.php" method="POST">
          <label>Felhasználónév: <input type="text" name="felhasznalonev"/></label> <br/>
          <label>Jelszó: <input type="password" name="jelszo"/></label> <br/>
          <input type="submit" name="login"/> <br/><br/>
        </form>
        <?php echo $uzenet . "<br/>"; ?>
      </section>

      <!-- Lábléc -->

      <footer>
        <p>&copy; Webtervezés gyakorlat</p>
      </footer>
    </main>
  </body>
</html>