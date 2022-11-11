<?php
  session_start();
  include "kozos.php";
  $fiokok = loadUsers("users.txt");

  $hibak = [];

  // űrlapfeldolgozás

  if (isset($_POST["regiszt"])) { 
    if (!isset($_POST["felhasznalonev"]) || trim($_POST["felhasznalonev"]) === "")
      $hibak[] = "A felhasználónév megadása kötelező!";

    if (!isset($_POST["jelszo"]) || trim($_POST["jelszo"]) === "" || !isset($_POST["jelszo2"]) || trim($_POST["jelszo2"]) === "")
      $hibak[] = "A jelszó és az ellenőrző jelszó megadása kötelező!";

    if (!isset($_POST["eletkor"]) || trim($_POST["eletkor"]) === "")
      $hibak[] = "Az életkor megadása kötelező!";

    if (!isset($_POST["nem"]) || trim($_POST["nem"]) === "")
      $hibak[] = "A nem megadása kötelező!";

    if (!isset($_POST["hobbik"]) || count($_POST["hobbik"]) < 2)
      $hibak[] = "Legalább 2 hobbit kötelező kiválasztani!";

    $felhasznalonev = $_POST["felhasznalonev"];
    $jelszo = $_POST["jelszo"];
    $jelszo2 = $_POST["jelszo2"];
    $eletkor = $_POST["eletkor"];
    $nem = NULL;
    $hobbik = NULL;

    if (isset($_POST["nem"]))
      $nem = $_POST["nem"];
    if (isset($_POST["hobbik"]))
      $hobbik = $_POST["hobbik"];

    foreach ($fiokok as $fiok) {
      if ($fiok["felhasznalonev"] === $felhasznalonev)
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
      $fiokok[] = ["felhasznalonev" => $felhasznalonev, "jelszo" => $jelszo, "eletkor" => $eletkor, "nem" => $nem, "hobbik" => $hobbik];
      saveUsers("users.txt", $fiokok);
      $siker = TRUE;
      header("Location: login.php");
    } else {                    // sikertelen regisztráció
      $siker = FALSE;
    }
  }
?>

<!DOCTYPE html>
<html lang="hu">
  <head>
    <title>Regisztráció</title>
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
            <li><a href="login.php">Bejelentkezés</a></li>
            <li class="active"><a href="signup.php">Regisztráció</a></li>
          <?php } ?>
        </ul>
      </nav>

      <!-- Az oldal tartalma -->

      <section id="content">
        <h2>Regisztráció</h2>
        <hr/>

        <!-- Regisztrációs űrlap -->

        <!-- Mivel az űrlapon egy fájlfeltöltés opciót is elhelyezünk, ezért az enctype="multipart/form-data" attribútumot is használnunk kell -->
        <form class="signup" action="signup.php" method="POST" enctype="multipart/form-data">
          <label>Felhasználónév: <input type="text" name="felhasznalonev" value="<?php if (isset($_POST['felhasznalonev'])) echo $_POST['felhasznalonev']; ?>"/></label> <br/>
          <label>Jelszó: <input type="password" name="jelszo"/></label> <br/>
          <label>Jelszó ismét: <input type="password" name="jelszo2"/></label> <br/>
          <label>Életkor: <input type="number" name="eletkor" value="<?php if (isset($_POST['eletkor'])) echo $_POST['eletkor']; ?>"/></label> <br/>
          Nem:
          <label><input type="radio" name="nem" value="F" <?php if (isset($_POST['nem']) && $_POST['nem'] === 'F') echo 'checked'; ?>/> Férfi</label>
          <label><input type="radio" name="nem" value="N" <?php if (isset($_POST['nem']) && $_POST['nem'] === 'N') echo 'checked'; ?>/> Nő</label>
          <label><input type="radio" name="nem" value="E" <?php if (isset($_POST['nem']) && $_POST['nem'] === 'E') echo 'checked'; ?>/> Egyéb</label> <br/>
          Hobbik:
          <label><input type="checkbox" name="hobbik[]" value="programozás" <?php if (isset($_POST['hobbik']) && in_array('programozás', $_POST['hobbik'])) echo 'checked'; ?>/> Programozás</label>
          <label><input type="checkbox" name="hobbik[]" value="főzés" <?php if (isset($_POST['hobbik']) && in_array('főzés', $_POST['hobbik'])) echo 'checked'; ?>/> Főzés</label>
          <label><input type="checkbox" name="hobbik[]" value="macskázás" <?php if (isset($_POST['hobbik']) && in_array('macskázás', $_POST['hobbik'])) echo 'checked'; ?>/> Macskázás</label>
          <label><input type="checkbox" name="hobbik[]" value="mémnézegetés" <?php if (isset($_POST['hobbik']) && in_array('mémnézegetés', $_POST['hobbik'])) echo 'checked'; ?>/> Mémnézegetés</label>
          <label><input type="checkbox" name="hobbik[]" value="alvás" <?php if (isset($_POST['hobbik']) && in_array('alvás', $_POST['hobbik'])) echo 'checked'; ?>/> Alvás</label> <br/>
          <!-- Fájlfeltöltés űrlapmező -->
          <label>Profilkép: <input type="file" name="profile-pic" accept="image/*"/></label> <br/>
          <input type="submit" name="regiszt"/> <br/><br/>
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
      </section>

      <!-- Lábléc -->

      <footer>
        <p>&copy; Webtervezés gyakorlat</p>
      </footer>
    </main>
  </body>
</html>