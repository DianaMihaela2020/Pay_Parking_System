import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Parking {

    public static void main(String[] args) {


        ArrayList<LocParcare> parcare = new ArrayList<>();

        //Crearea a 10 locuri de pacare goale, cu un un nrLoc atribuit pentru fiecare in parte.(Se creeaza parcarea
        // cu cele 1 locuri de parcare libere.)
        for (int i = 0; i < 10; i++) {
            LocParcare auxLoc = new LocParcare(i + 1);
            parcare.add(auxLoc);
        }

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Introduceti tipul de actiune:");
            int actiune = Integer.parseInt(scanner.nextLine());

            switch(actiune){
                //Pentru a afla nr. de locuri libere se introduce nr 1
                case 1:
                    int numarLocuriLibere =  getNrLocuriParcareLibere(parcare);
                    System.out.println("Numar locuri libere: "+  numarLocuriLibere);
                    break;
                 // Pentru a intra in parcare, se va introduce nr. 2
                case 2:
                    System.out.println("Introdu nr. de inmatriculare: ");
                    String numarInmatriculare = scanner.nextLine();
                    // Se verifica daca o masina cu acest numar de inmatriculare exista in parcare.
                    if(getLocParcarePentruNumarMasina(numarInmatriculare, parcare) == null) {
                        System.out.println("Locuri disponibile in parcare sunt: ");
                        ArrayList<Integer> locuriLibere =  getLocuriLibere(parcare);
                        for(int i =0; i< locuriLibere.size(); i++) {
                            System.out.print(locuriLibere.get(i) + " ");
                        }
                        System.out.println("Introduceti locul de parcare liber pe care doriti sa parcati.");
                        int nrLocAles = Integer.parseInt(scanner.nextLine());
                        if(locuriLibere.contains(nrLocAles)){
                            parcare =   ocupaLocDeParcare(parcare, nrLocAles, numarInmatriculare);
                        }else{
                            if(nrLocAles <= 10){
                                System.out.println("Locul de prcare ales este ocupat.");
                            }else{
                                System.out.println("Locul de parcare ales nu este valid.");
                            }
                            System.out.println("Introduceti locul de parcare liber pe care doriti sa parcati sau" +
                                    " introduceti 0 daca doriti sa nu mai intrati in parcare.");
                            nrLocAles = Integer.parseInt(scanner.nextLine());
                            if(nrLocAles==0){
                                break;
                            }else{
                                if(locuriLibere.contains(nrLocAles)){
                                    parcare =   ocupaLocDeParcare(parcare, nrLocAles, numarInmatriculare);
                                }else{
                                    System.out.println("Locul de parcare ales este deja ocupat sau nu este valid. Reveniti.");
                                }
                            }

                        }

                    } else{
                        System.out.println("Numarul de inmatriculare introdus se afla deja in parcare.");
                    }

                    break;
                    // Pentru a iesi din parcare se va introduce numarul 3.
                case 3:
                    System.out.println("Inroduceti numarul de inmatriculare al masinii care iese din parcare.");
                    String numarMasina = scanner.nextLine();
                    if(getLocParcarePentruNumarMasina(numarMasina, parcare) == null){
                        System.out.println("Masina cu nr. de inmatriculare " + numarMasina + " nu este parcata aici.");
                        break;
                    }else{

                      parcare= eliberareLocParcare(parcare, numarMasina);
                    }
                    break;

                    // Pentru a interoga lista de masini se va introduce numarul 4.
                case 4:
                    afisareListaMasini(parcare);
                    break;

            }
        }


    }

    public static int getNrLocuriParcareLibere(ArrayList<LocParcare> configuratie) {
        int contor = 0;
        for (int i = 0; i < 10; i++) {
            boolean ocupat = configuratie.get(i).isEsteOcupat();
            if (!ocupat) {
                contor++;
            }
        }
        return contor;
    }

    public static ArrayList<Integer> getLocuriLibere(ArrayList<LocParcare> configuratie){
        ArrayList<Integer> locuriLibere = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            boolean ocupat = configuratie.get(i).isEsteOcupat();
            if (!ocupat) {
                int nrLocParcare = configuratie.get(i).getNrLocParcare();
                locuriLibere.add(nrLocParcare);
            }
        }
        return locuriLibere;
    }

    public static ArrayList<LocParcare>  ocupaLocDeParcare(ArrayList<LocParcare> configuratie, int nrLoc, String nrMasina) {
        LocParcare locNouOcupat = new LocParcare(nrMasina, nrLoc);
        configuratie.set(nrLoc-1, locNouOcupat);
        return configuratie;
    }

    public static  ArrayList<LocParcare> eliberareLocParcare (ArrayList<LocParcare> configuratie, String nrMasina){
        LocParcare loc= getLocParcarePentruNumarMasina(nrMasina, configuratie);

        LocalDateTime timpIesire =  LocalDateTime.now();
        int sumaDePlatit = getSumaDePlatit(loc.getOraIntrareParcare(), timpIesire);
        System.out.println(" Masina cu nr. e inmatriculare " + nrMasina + " are de plata " + sumaDePlatit + "lei " +
                " a intrat in parcare " + loc.getOraIntrareParcare() + " si a iesit la ora" + timpIesire);

        LocParcare locLiber = new LocParcare(loc.getNrLocParcare());
        configuratie.set(loc.getNrLocParcare()-1, locLiber);
        return configuratie;
    }

    public static LocParcare getLocParcarePentruNumarMasina(String numarMasina,ArrayList<LocParcare> configuratie ){
        for(int i = 0; i<configuratie.size(); i++){
            if(configuratie.get(i).isEsteOcupat()){
                if(configuratie.get(i).getNrInmatriculare().equals(numarMasina))
                    return configuratie.get(i);
            }
        }
        return null;
    }

    public static int getSumaDePlatit (LocalDateTime timpIntrare, LocalDateTime timpIesire){
        long ore = ChronoUnit.HOURS.between(timpIntrare, timpIesire);
        long minute = ChronoUnit.MINUTES.between(timpIntrare, timpIesire);
        int suma = 0;
        if(ore == 0){
            suma = 10;
        }else{
            suma = (int)ore * 5 + 5;
            if(minute > 0){
                suma = suma + 5;
            }

        }
        return suma;
    }

    public static void afisareListaMasini(ArrayList<LocParcare> configuratie ){
        for(int i =0; i<configuratie.size(); i++){
            if (configuratie.get(i).isEsteOcupat()){
                System.out.println("Locul de parcare cu numarul " + configuratie.get(i).getNrLocParcare() + " este ocupat " +
                        "de masina cu numarul de inmatriculare " + configuratie.get(i).getNrInmatriculare() + " incepand " +
                        "cu ora " + configuratie.get(i).getOraIntrareParcare());
            }else{
                System.out.println("Locul de parcare cu numarul " + configuratie.get(i).getNrLocParcare() + " nu este ocupat ");
            }
        }

    }


}
