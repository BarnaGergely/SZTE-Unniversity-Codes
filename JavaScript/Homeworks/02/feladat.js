// Nev: Barna Gergely
// Neptun: FJKXGG
// h: h144988

function matek(valtozo) {
    if (valtozo == null) {
        return 0;
    } else if (typeof valtozo === "string") {
        return 1;
    } else if (typeof valtozo === "number" && Number.isInteger(valtozo)) {
          let temp;
            if(valtozo === 0) {
              return 1;
            }
            else if (valtozo % 2 === 0) {
                temp = 1;
                for (let i = 0; i < valtozo; i++) {
                    temp = temp*valtozo;
                }
            } else {
                temp = ((valtozo-1)*(valtozo-1))
            }
            return temp;
    } else {
        return null;
    }
}

console.log(matek(10));
console.log(matek("5"));
console.log(matek("alma"));
console.log(matek(5));
console.log(matek(2));
console.log(matek(0));
console.log(matek(1.48));
console.log(matek());