<?php
	// egy Komment osztály létrehozása, amely egy felhasználói hozzászólást fog leírni

	class Komment {
		// adattagok

		private $szerzo;
		private $datum;
		private $szoveg;

		// konstruktor

		public function __construct($szerzo, $datum, $szoveg) {
			$this->szerzo = $szerzo;
			$this->datum = $datum;
			$this->szoveg = $szoveg;
		}

		// getterek és setterek

		public function getSzerzo() {
			return $this->szerzo;
		}

		public function setSzerzo($ertek) {
			$this->szerzo = $ertek;
		}

		public function getDatum() {
			return $this->datum;
		}

		public function setDatum($ertek) {
			$this->datum = $ertek;
		}

		public function getSzoveg() {
			return $this->szoveg;
		}

		public function setSzoveg($ertek) {
			$this->szoveg = $ertek;
		}
	}
?>