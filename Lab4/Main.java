import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        var residents = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Resident("R" + i)).toArray(Resident[]::new);

        List<Resident> residentList = new ArrayList<>(Arrays.asList(residents));

        residentList.sort(Comparator.comparing(Resident::getName));

        var hospitals = new Hospital[]{
                new Hospital("H0", 1),
                new Hospital("H1", 2),
                new Hospital("H2", 2)
        };

        Set<Hospital> hospitalSet = new TreeSet<>(Arrays.asList(hospitals));

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();

        resPrefMap.put(residents[0], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        resPrefMap.put(residents[1], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        resPrefMap.put(residents[2], Arrays.asList(hospitals[0], hospitals[1]));
        resPrefMap.put(residents[3], Arrays.asList(hospitals[0], hospitals[1]));

        Map<Hospital, List<Resident>> hosPrefMap = new HashMap<>();

        hosPrefMap.put(hospitals[0], Arrays.asList(residents[3], residents[0], residents[1], residents[2]));
        hosPrefMap.put(hospitals[1], Arrays.asList(residents[0], residents[2], residents[1]));
        hosPrefMap.put(hospitals[2], Arrays.asList(residents[0], residents[1], residents[3]));


        System.out.println("Residents who find acceptable H0 and H2:");

        var preferredHospitals = Arrays.asList(hospitals[0], hospitals[1]);

        residentList.stream()
                .filter(resident -> resPrefMap.get(resident).containsAll(preferredHospitals))
                .forEach(System.out::println);


        System.out.println("\nHospitals that have R0 as their top preference:");
        hospitalSet.stream()
                .filter(hospital -> hosPrefMap.get(hospital).get(0).equals(residents[0]))
                .forEach(System.out::println);
    }
}
