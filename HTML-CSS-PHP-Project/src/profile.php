<?php
session_start();
include "kozos.php";

if (!isset($_SESSION["user"])) {
  // ha a felhasználó nincs belépve (azaz a "user" munkamenet-változó értéke nem került korábban beállításra), akkor a login.php-ra navigálunk
  header("Location: bejelentkezes.php");
}

function nemet_konvertal($betujel)
{    // egy segédfüggvény, amely visszaadja a betűjelnek megfelelő nemet
  switch ($betujel) {
    case "F":
      return "férfi";
      break;
    case "N":
      return "nő";
      break;
    case "E":
      return "egyéb";
      break;
  }
}
?>

<!DOCTYPE html>
<html lang="hu">

<head>
  <title>Profil - Óvervécs</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <link rel="icon" type="image/x-icon" href="./assets/base/Logo.svg">
  <link rel="stylesheet" href="./css/style.css" />
</head>

<body>
  <!-- Fejléc -->

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

    <section id="content">
      <h2>Profilom</h2>
      <hr />

      <?php
      $utvonal = "images";                                    // ebben a mappában találjuk a profilképeket
      $felhasznalonev = $_SESSION["user"]["username"];  // a bejelentkezett felhasználó neve (ez lesz a profilképének a neve is)
      $kiterjesztesek = ["png", "jpg", "jpeg"];               // az engedélyezett kiterjesztések

      // a profilkép elérési útvonala (a kozos.php-ban definiált getProfilePicture() függvénnyel kaphatjuk meg)

      $profilkep = getProfilePicture($utvonal, $felhasznalonev, $kiterjesztesek);
      ?>

      <table class="profile-data">
        <tr>
          <th colspan="2">
            <img src="<?php echo $profilkep; ?>" alt="Profilkép" height="200" />
            <?php if ($_SESSION["user"]["username"] !== "default") { /* a "default" nevű példa felhasználó esetén ne engedélyezzük a profilkép módosítását */ ?>
              <form action="profile.php" method="POST" enctype="multipart/form-data">
                <input type="file" name="profile-pic" accept="image/*" />
                <input type="submit" name="upload-btn" value="Profilkép módosítása" />
              </form>
            <?php } ?>
          </th>
        </tr>
        <tr>
          <th>Felhasználónév:</th>
          <td><?php echo $_SESSION["user"]["username"]; ?></td>
        </tr>
        <tr>
          <th>Életkor:</th>
          <td><?php echo $_SESSION["user"]["eletkor"]; ?></td>
        </tr>
        <tr>
          <th>Nem:</th>
          <td><?php echo nemet_konvertal($_SESSION["user"]["sex"]); ?></td>
        </tr>
        <tr>
          <th>Kedvenc hősök:</th>
          <td><?php echo implode(", ", $_SESSION["user"]["hero"]); ?></td>
        </tr>
      </table>

      <?php
      // a profilkép módosítását elvégző PHP kód

      if (isset($_POST["upload-btn"]) && is_uploaded_file($_FILES["profile-pic"]["tmp_name"])) {  // ha töltöttek fel fájlt...
        $fajlfeltoltes_hiba = "";                                       // változó a fájlfeltöltés során adódó esetleges hibaüzenet tárolására
        uploadProfilePicture($_SESSION["user"]["username"]);      // a kozos.php-ban definiált profilkép feltöltést végző függvény meghívása

        $kit = strtolower(pathinfo($_FILES["profile-pic"]["name"], PATHINFO_EXTENSION));    // a feltöltött profilkép kiterjesztése
        $utvonal = "images/" . $_SESSION["user"]["username"] . "." . $kit;            // a feltöltött profilkép teljes elérési útvonala

        // ha nem volt hiba a fájlfeltöltés során, akkor töröljük a régi profilképet, egyébként pedig kiírjuk a fájlfeltöltés során adódó hibát

        if ($fajlfeltoltes_hiba === "") {
          if ($utvonal !== $profilkep && $profilkep !== "images/default.png") {   // az ugyanolyan névvel feltöltött képet és a default.png-t nem töröljük
            unlink($profilkep);                         // régi profilkép törlése
          }

          header("Location: profile.php");              // weboldal újratöltése
        } else {
          echo "<p>" . $fajlfeltoltes_hiba . "</p>";
        }
      }
      ?>
    </section>

    <nav>
      <ul>
        <?php if (isset($_SESSION["user"])) { ?>
          <li class="active"><a href="profile.php">Profilom</a></li>
          <li><a href="kijelentkezes.php">Kijelentkezés</a></li>
        <?php } else { ?>
          <li><a href="bejelentkezes.php">Bejelentkezés</a></li>
          <li><a href="regisztracio.php">Regisztráció</a></li>
        <?php } ?>
      </ul>
    </nav>
  </main>

  <!--
       Lábléc 
          <nav>
        <ul>
          <li><a href="index.php">Főoldal</a></li>
          <?php if (isset($_SESSION["user"])) { ?>
            <li class="active"><a href="profile.php">Profilom</a></li>
            <li><a href="logout.php">Kijelentkezés</a></li>
          <?php } else { ?>
            <li><a href="login.php">Bejelentkezés</a></li>
            <li><a href="signup.php">Regisztráció</a></li>
          <?php } ?>
        </ul>
      </nav>

    
    -->

  <footer>Copyright 2022 - Halápi Vivien, Barna Gergely</footer>
</body>

</html>