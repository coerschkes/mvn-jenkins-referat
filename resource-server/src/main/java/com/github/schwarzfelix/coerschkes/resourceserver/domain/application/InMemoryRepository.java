package com.github.schwarzfelix.coerschkes.resourceserver.domain.application;

import com.github.schwarzfelix.coerschkes.resourceserver.domain.entity.CampingTent;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

//TODO: Test cases
@Repository
public class InMemoryRepository implements CampingTentRepository {
    private final ResourceLoader resourceLoader;
    private final List<CampingTent> campingTents;

    public InMemoryRepository(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.campingTents = new ArrayList<>();
        this.campingTents.addAll(this.initialTents());
    }

    @Override
    public List<CampingTent> findAll() {
        return this.campingTents;
    }

    @Override
    public Optional<CampingTent> findById(final long id) {
        return this.campingTents.stream().filter(tent -> tent.id() == id).findFirst();
    }

    @Override
    public void deleteById(final long id) {
        this.findById(id).ifPresentOrElse(this.campingTents::remove, () -> {
            throw new EntityNotFoundException(id);
        });
    }

    @Override
    public void orderSingle(final long id) {
        var tent = this.findById(id).orElseThrow();
        if (tent.stock() == 0) {
            throw new IllegalStateException("Stock is empty!");
        }
        int index = this.campingTents.indexOf(tent);
        this.deleteById(id);
        this.campingTents.add(index, tent.withStock(tent.stock() - 1));
    }

    private String loadImageAsBase64(final String name) {
        try {
            final byte[] content = this.resourceLoader.getResource("classpath:images/" + name).getContentAsByteArray();
            return Base64.getEncoder().encodeToString(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CampingTent> initialTents() {
        return List.of(
                new CampingTent(1L, "ZELT MINI PACK PREMIUM 2", """
                        Das Mini Pack Premium von Mil-Tec ist ein ideales Einsteigerzelt für 2 Personen. Einfacher Auf-und Abbau sowie das geringe Gewicht machen es perfekt für deine Touren.

                        2-Personen-Zelt
                        geringes Gewicht
                        schneller Aufbau
                        innenliegendes Insektennetz hinter dem Eingang
                        trittfestes PE-Bodengewebe
                        inklusive Packsack
                        Wassersäule Zelthaut: 1500 mm
                        Wassersäule Boden: 5000 mm
                        Maße Außen (HxBxT): 100 x 145 x 205 cm
                        Packmaß: 57 x Ø 16 cm
                        Gewicht: 1,9 kg

                        Material Außen: 100% 190T Polyester, Polyurethan beschichtet
                        Material Boden: Polyester (Oxford)
                        Gestänge: Stahl""", "64,99€", "100 x 145 x 205 cm", 30, 2,
                        this.loadImageAsBase64("mil-tec-mini-pack-premium-2.jpg")),
                new CampingTent(2L, "EINMANNZELT RECOM", """
                        Das Recom Einmannzelt von Mil-Tec punktet vor allem durch seine kompakte, wind- und sturmsichere Form. Der großzügige Eingangsbereich sorgt für ein bequemes Ein- und Aussteigen. Ein Vorraum am Zelt bietet dir zudem das Verstauen und Abstellen von Ausrüstung. Lüftungsöffnungen rechts und links sorgen für eine gute Belüftung, ein Moskitonetz im Eingangsbereich schützt zuverlässig vor dem Eindringen von Insekten und Co.

                        Einmannzelt in kompakter, wind- und sturmsicherer Form
                        Reißverschluss im Eingangsbereich ermöglicht bequemen Zugang
                        leichter Auf- und Abbau
                        geringes Packmaß
                        Abstellmöglichkeit für Ausrüstung im Vorraum
                        Lüfter rechts und links
                        Moskitonetz im Eingang
                        inkl. Seile, Heringe, Zeltstangen und Packsack
                        Zelteigenschaften

                        Personen: 1
                        Konstruktion: Tunnelzelt
                        Maße außen (HxBxT): 240 x 135 x 85 cm
                        Wassersäule außen: 1000 mm/cm²
                        Wassersäule innen: 800 mm/cm²
                        Wassersäule Boden: 2000 mm/cm²
                        Packmaß (HxBxT): 55 x 11,5 x 11,5 cm
                        Gewicht: 2400 g

                        Material

                        Zelthaut: 100% Polyester, Polyurethan beschichtet
                        Innenzelt: 100% Polyester, Polyurethan beschichtet
                        Zeltboden: 100% Polyester (Oxford), Polyurethan beschichtet
                        Gestänge: Fiberglas
                        Transportsack: 100% Polyester""", "109,99€", "240 x 135 x 85 cm", 5, 1,
                        this.loadImageAsBase64("mil-tec-einmannzelt-recom.jpg")),
                new CampingTent(3L, "ZWEIMANNZELT IGLU STANDARD", """
                        Leicht aufzubauendes 2-Personen-Zelt mit beschichtetem, wasserabweisendem Außenmaterial. Dank des geringen Gewichtes ist es der ideal Begleiter für kurze Touren und Festivalaufenthalte.

                        - Start Up Igluzelt für 2 Personen
                        - schneller und einfacher Aufbau
                        - geringes Gewicht
                        - innenliegendes Insektennetz mit Reißverschluss
                        - inkl. Abspannschnur und Heringen
                        - inkl. Packsack

                        Material Außen: 100% 190T Polyester, Polyurethan beschichtet
                        Material Boden: Polyethylen
                        Wassersäule Zelthaut: 800 mm
                        Maße Außen (HxBxT): 100 x 145 x 205 cm
                        Packmaß: 61 x Ø 11 cm
                        Gewicht: 1,8 kg
                        Gestänge: Fiberglas""", "49,99€", "100 x 145 x 205 cm", 23, 2,
                        this.loadImageAsBase64("mil-tec-zweimannzelt-iglu-standard.jpg")),
                new CampingTent(4L, "ZELT MINI PACK STANDARD 2", """
                        Das ideale Zweimann Zelt mit besonders leichtem Gewicht für große Abenteuer.

                        - 2-Personen-Zelt
                        - geringes Gewicht
                        - schneller Aufbau
                        - innenliegendes Insektennetz hinter dem Eingang
                        - trittfestes PE-Bodengewebe
                        - inklusive Packsack

                        Material Außen: 100% 190T Polyester, Polyurethan beschichtet
                        Material Boden: Polyethylen
                        Wassersäule Außenzelt: 800 mm/cm²
                        Maße Außen (HxBxT): 100 x 145 x 205 cm
                        Packmaß: 61 x Ø 14 cm
                        Gewicht: 1,8 kg""", "54,99€", "100 x 145 x 205 cm", 67, 2,
                        this.loadImageAsBase64("mil-tec-mini-pack-standard-2.jpg"))
        );
    }
}
