// Nev: Barna Gergely
// Neptun: FJKXGG
// h: h144988

function clear(tomb) {
  if (tomb !== null && tomb !== undefined) {
    if (typeof tomb === "object" && tomb instanceof Array) {
      let forditottTomb = [];
      let ujtomb = [];
      for (const elem of tomb) {
        if (typeof elem === "number") {
          ujtomb.push(elem);
        }
        if (typeof elem === "string" && elem.length>0) {
          let szoveg = elem.substring(0, 2);
          szoveg = szoveg.toUpperCase();
          ujtomb.push(szoveg);
        }
      }
      for (let index = 0; index < ujtomb.length; index++) {
        forditottTomb[ujtomb.length-index-1] = ujtomb[index];
      }
      return forditottTomb;
    }
  } else {
    return 0;
  }
}

//console.log(clear([2, 5, 3, 'heyho', null, 7]));
