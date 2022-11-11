<?php
session_start();
include "kozos.php";
$fiokok = loadUsers("users.txt");

$hibak = [];

// űrlapfeldolgozás

if (isset($_POST["regist-btn"])) {
  if (!isset($_POST["username"]) || trim($_POST["username"]) === "")
    $hibak[] = "A felhasználónév megadása kötelező!";

  if (!isset($_POST["passwd"]) || trim($_POST["passwd"]) === "" || !isset($_POST["passwd-check"]) || trim($_POST["passwd-check"]) === "")
    $hibak[] = "A jelszó és az ellenőrző jelszó megadása kötelező!";

  if (!isset($_POST["eletkor"]) || trim($_POST["eletkor"]) === "")
    $hibak[] = "Az életkor megadása kötelező!";

  if (!isset($_POST["sex"]) || trim($_POST["sex"]) === "")
    $hibak[] = "A nem megadása kötelező!";

  if (!isset($_POST["hero"]) || count($_POST["hero"]) < 1)
    $hibak[] = "Legalább 1 hőst kötelező kiválasztani!";

  $felhasznalonev = $_POST["username"];
  $jelszo = $_POST["passwd"];
  $jelszo2 = $_POST["passwd-check"];
  $eletkor = $_POST["eletkor"];
  $nem = NULL;
  $hobbik = NULL;

  if (isset($_POST["sex"]))
    $nem = $_POST["sex"];
  if (isset($_POST["hero"]))
    $hobbik = $_POST["hero"];

  foreach ($fiokok as $fiok) {
    if ($fiok["username"] === $felhasznalonev)
      $hibak[] = "A felhasználónév már foglalt!";
  }

  if (strlen($jelszo) < 5)
    $hibak[] = "A jelszónak legalább 5 karakter hosszúnak kell lennie!";

  if ($jelszo !== $jelszo2)
    $hibak[] = "A jelszó és az ellenőrző jelszó nem egyezik!";

  if ($eletkor < 18)
    $hibak[] = "Csak 18 éves kortól lehet regisztrálni!";

  $fajlfeltoltes_hiba = "";               // változó a fájlfeltöltés során adódó esetleges hibaüzenet tárolására
  uploadProfilePicture($felhasznalonev);  // a kozos.php-ban definiált profilkép feltöltést végző függvény meghívása

  if ($fajlfeltoltes_hiba !== "")         // ha volt hiba a fájlfeltöltés során, akkor hozzáírjuk a hibaüzenetet a $hibak tömbhöz
    $hibak[] = $fajlfeltoltes_hiba;

  if (count($hibak) === 0) {   // sikeres regisztráció
    $jelszo = password_hash($jelszo, PASSWORD_DEFAULT);
    $fiokok[] = ["username" => $felhasznalonev, "passwd" => $jelszo, "eletkor" => $eletkor, "sex" => $nem, "hero" => $hobbik];
    saveUsers("users.txt", $fiokok);
    $siker = TRUE;
    header("Location: bejelentkezes.php");
  } else {                    // sikertelen regisztráció
    $siker = FALSE;
  }
}
?>


<!DOCTYPE html>
<html lang="hu">

<head>
  <title>Regisztráció - Óvervécs</title>
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

    input,
    select,
    textarea,
    fieldset {
      margin-bottom: 1rem;
    }
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

    <H1>Regisztráció</H1>
    <div id="login">

      <div>
        <form class="signup" action="regisztracio.php" method="POST" enctype="multipart/form-data">
          <fieldset>
            <legend>Regisztrációs adatok</legend>
            <label>Teljes név: <input type="text" name="full-name" size="25" value="<?php if (isset($_POST['full-name'])) echo $_POST['full-name']; ?>"/></label> <br />
            <label>Felhasználónév: <input type="text" name="username" value="<?php if (isset($_POST['username'])) echo $_POST['username']; ?>"/></label> <br />
            <label>Jelszó: <input type="password" name="passwd" /></label> <br />
            <label>Jelszó ismét: <input type="password" name="passwd-check" /></label> <br />
            <label>Életkor: <input type="number" name="eletkor" value="<?php if (isset($_POST['eletkor'])) echo $_POST['eletkor']; ?>"/></label> <br/>
            <br />
            <label>E-mail: <input type="email" name="email" value="<?php if (isset($_POST['email'])) echo $_POST['email']; ?>"/></label> <br />
          </fieldset>

          <label for="education">Legmagasabb elért rang:</label>
          <select id="education">
            <option value="bronze" selected>Bronze</option>
            <option value="silver">Silver</option>
            <option value="gold">Gold</option>
            <option value="platinum">Platinum</option>
            <option value="diamond">Diamond</option>
          </select> <br />

          <label>Profilkép: <input type="file" name="profile-pic" accept="image/*"/></label> <br />

          Nem:
          <label for="op1">Férfi:</label>
          <input type="radio" id="op1" name="sex" value="F" <?php if (isset($_POST['sex']) && $_POST['sex'] === 'F') echo 'checked'; ?>/>
          <label for="op2">Nő:</label>
          <input type="radio" id="op2" name="sex" value="N" <?php if (isset($_POST['sex']) && $_POST['sex'] === 'N') echo 'checked'; ?>/>
          <label for="op3">Egyéb:</label>
          <input type="radio" id="op3" name="sex" value="E" <?php if (isset($_POST['sex']) && $_POST['sex'] === 'E') echo 'checked'; ?>/>
          <br />

          Melyik hősökkel szeretsz játszani? <br />
          <label for="hero-1">D.Va:</label>
          <input type="checkbox" id="hero-1" name="hero[]" value="dva" <?php if (isset($_POST['hero']) && in_array('dva', $_POST['hero'])) echo 'checked'; ?>/>
          <label for="hero-2">Mercy:</label>
          <input type="checkbox" id="hero-2" name="hero[]" value="mercy" <?php if (isset($_POST['hero']) && in_array('mercy', $_POST['hero'])) echo 'checked'; ?>/>
          <label for="hero-3">Widowmaker:</label>
          <input type="checkbox" id="hero-3" name="hero[]" value="widowsmaker" <?php if (isset($_POST['hero']) && in_array('widowsmaker', $_POST['hero'])) echo 'checked'; ?>/> <br />

          <label for="introduction">Bemutatkozás (max. 200 karakter):</label> <br />
          <textarea id="introduction" name="intro" maxlength="200"></textarea> <br />

          <input type="reset" name="reset-btn" value="Adatok törlése" />
          <input type="submit" name="regist-btn" value="Adatok elküldése" />
        </form>

        <?php
        if (isset($siker) && $siker === TRUE) {  // ha nem volt hiba, akkor a regisztráció sikeres
          echo "<p>Sikeres regisztráció!</p>";
        } else {                                // az esetleges hibákat kiírjuk egy-egy bekezdésben
          foreach ($hibak as $hiba) {
            echo "<p>" . $hiba . "</p>";
          }
        }
        ?>

        <p style="text-align: center;">Már van fiókod? - <a href="bejelentkezes.php">Bejelentkezés</a></p>
      </div>

    </div>
    <!--                    Content                    -->
  </main>

  <footer>Copyright 2022 - Halápi Vivien, Barna Gergely</footer>
</body>

</html>