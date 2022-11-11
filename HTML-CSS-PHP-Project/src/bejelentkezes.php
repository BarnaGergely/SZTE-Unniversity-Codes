<?php
  session_start();
  include "kozos.php";              
  $fiokok = loadUsers("users.txt"); 

  $uzenet = ""; 

  // űrlapfeldolgozás

  if (isset($_POST["submit-btn"])) {
    if (!isset($_POST["username"]) || trim($_POST["username"]) === "" || !isset($_POST["passwd"]) || trim($_POST["passwd"]) === "") {
      $uzenet = "<strong>Hiba:</strong> Adj meg minden adatot!";
    } else {
      $felhasznalonev = $_POST["username"];
      $jelszo = $_POST["passwd"];

      $uzenet = "Sikertelen belépés! A belépési adatok nem megfelelők!";

      foreach ($fiokok as $fiok) {
        if ($fiok["username"] === $felhasznalonev && password_verify($jelszo, $fiok["passwd"])) { // sikeres bejelentkezés
          $uzenet = "Sikeres belépés!";
          $_SESSION["user"] = $fiok;           // a "user" nevű munkamenet-változó a bejelentkezett felhasználót reprezentáló tömböt fogja tárolni
          header("Location: profile.php");
        }
      }
    }
  }
?>

<!DOCTYPE html>
<html lang="hu">

<head>
    <title>Bejelentkezés - Óvervécs</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="icon" type="image/x-icon" href="./assets/base/Logo.svg">
    <link rel="stylesheet" href="./css/style.css" />

    <style>
        #login {
            display: flex;
            flex-direction: column;
            align-items: center;
            /*border: 3px solid white;*/
        }

        input, select, textarea, fieldset { margin-bottom: 1rem; }
    </style>
</head>

<body>
<header>
    <span style="float: left; padding-left: 2rem">
      <a href="./index.html" style="display: inline">
        <span class="logo">
          <img class="imgLogo" src="./assets/base/Logo.svg" alt="Óvervécs logo" />
          <img class="imgLogoText" src="./assets/base/Text_logo_light.svg" alt="Óvervécs" />
        </span>
      </a>
    </span>
    <div style="float: right; padding-right: 2rem">
        <nav style="display: inline">
        <span class="menubar">
          <a href="./hirek.php">Hírek</a>
          <a href="./jatekTortenete.html">Története</a>
          <a href="./jatekmodok.html">Játékmódok</a>
          <a href="./hosokBemutatasa.html">Hősök</a>
          <a href="./esemenyek.html">Események</a>
        </span>
        </nav>

        <a href="./bejelentkezes.php" style="display: inline">
        <span class="logo">
          <img class="imgLogin" src="./assets/base/Login.svg" alt="Bejelentkezés" />
        </span>
        </a>
    </div>
</header>
    <main>
        <!--                    Content                    -->
        <H1>Bejelentkezés</H1>

        <div id="login">

            <div>
                <form class="login" action="bejelentkezes.php" method="POST" enctype="application/x-www-form-urlencoded">
                    <fieldset>
                        <legend>Bejelentkezés</legend>
                        <label>Felhasználónév: <input type="text" name="username" required /></label> <br />
                        <label>Jelszó: <input type="password" name="passwd" required /></label> <br />
                    </fieldset>

                    <input type="submit" name="submit-btn" value="Belépés" />
                </form>
                <?php echo $uzenet . "<br/>"; ?>

                <p style="text-align: center;">Még nincs fiókod? - <a href="regisztracio.php">Regisztráció</a></p>
                
                <?php if (isset($_SESSION["user"])) { ?>
                  <p><a href="profile.php">Profilom</a></p>
                  <p><a href="kijelentkezes.php">Kijelentkezés</a></p>
                <?php } ?>
            </div>

        </div>
        <!--                    Content                    -->
    </main>

    <footer>
      Copyright 2022 - Halápi Vivien, Barna Gergely
    </footer>
</body>

</html>