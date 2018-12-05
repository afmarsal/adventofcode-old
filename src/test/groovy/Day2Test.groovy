import spock.lang.Specification

class Day2Test extends Specification {


    def "test 1 basic input"() {
        expect:
            Day2.test1(input) == output

        where:
            input    | output
            "abcdef" | [0, 0]
            "bababc" | [1, 1]
            "abbcde" | [1, 0]
            "abcccd" | [0, 1]
            "aabcdd" | [1, 0]
            "abcdee" | [1, 0]
            "ababab" | [0, 1]
    }

    def "test 1 checksum"() {
        expect:
            Day2.test1checksum(input) == output

        where:
            input          | output
            SAMPLE_INPUT_1 | 12
            FINAL_INPUT    | 7533
    }

    def "test 2 basic input"() {
        expect:
            Day2.test2(input) == output

        where:
            input          | output
            SAMPLE_INPUT_2 | "fgij"
            FINAL_INPUT    | "mphcuasvrnjzzkbgdtqeoylva"
    }

    public static final String SAMPLE_INPUT_1 = '''\
        abcde
        bababc
        abbcde
        abcccd
        aabcdd
        abcdee
        ababab'''.stripIndent()

    public static final String SAMPLE_INPUT_2 = '''\
        abcde
        fghij
        klmno
        pqrst
        fguij
        axcye
        wvxyz'''.stripIndent()

    static String FINAL_INPUT = '''\
        mphcuiszrnjzxwkbgdzqeoyxfa
        mihcuisgrnjzxwkbgdtqeoylia
        mphauisvrnjgxwkbgdtqeiylfa
        mphcuisnrnjzxwkbgdgqeoylua
        mphcuisurnjzxwkbgdtqeoilfi
        mkhcuisvrnjzowkbgdteeoylfa
        mphcoicvrnjzxwksgdtqeoylfa
        mxhcuisvrndzxwkbgdtqeeylfa
        dphcuisijnjzxwkbgdtqeoylfa
        mihvuisvrqjzxwkbgdtqeoylfa
        mphcuisrrnvzxwkbgdtqeodlfa
        mphtuisdrnjzxskbgdtqeoylfa
        mphcutmvsnjzxwkbgdtqeoylfa
        mphcunsvrnjzswkggdtqeoylfa
        mphcuisvrwjzxwkbpdtqeoylfr
        mphcujsdrnjzxwkbgdtqeovlfa
        mpfcuisvrdjzxwkbgdtteoylfa
        mppcuisvrpjzxwkbgdtqeoywfa
        mphcuisvrnjzxwkbfptqroylfa
        mphcuisvrnjzxwkbgstoeoysfa
        mphcufsvrnjzcwkbgdeqeoylfa
        mphcuissrnjzxwkbgdkquoylfa
        sphcuxsvrnjzxwkbgdtqioylfa
        mphcuiivrhjzxwkbgdtqevylfa
        echcuisvrnjzxwkbgltqeoylfa
        mphcuisvrljexwkbvdtqeoylfa
        mpjcuisvrnjzxwkhidtqeoylfa
        mphcuisvrfjzmwkbgdtqeoylfl
        mwhcuisvrnjzxwkbgdtqeoytfm
        mphcuisvrsjzxwkbgdaqeoylfh
        mohcuisvrnjzxwkbgdtqtoymfa
        maycuisvrnjzxwkbgdtqboylfa
        pphcuisvqnjzxwkbgdtqeoylfd
        mprcuisvrnjtxwmbgdtqeoylfa
        mfhcuisgrnjzxckbgdtqeoylfa
        mphiubsvrnjzxwkbgdtqeoyufa
        dphctisvrnjzxwkbgdtqeoylfk
        mphcuisvrnjznwksgdtqeoyzfa
        mpwcuisvrnjziwkbgdtqaoylfa
        mphduzsvrnjznwkbgdtqeoylfa
        mphccisvrnjzxwebgdtqeoylqa
        xphcuisvrnjzxwkfvdtqeoylfa
        mphcupsvrnjzxwkbgdtfeoylpa
        mphcuisvrtjzjwkbgdtqeoylfe
        mpbcuisvrnjzxwkbgdmieoylfa
        mphcuisvrnjzxwkbgjtqetylaa
        mphcuisvrnjzxwpbgdtgdoylfa
        ophcufsvrqjzxwkbgdtqeoylfa
        iphcuhsvrnjzxwkbgetqeoylfa
        mphcuisvunjzxwwbgdtqeoylqa
        mphcpisvrnjzowkbgdtveoylfa
        mphcuisvrnjzxhkbgdtqeotlla
        mphcuisvrnjzxwkbodtgeoylha
        mphcuisvrjjzxwkbwdtqtoylfa
        mphcwisvrnjnxwkbgjtqeoylfa
        mplcuicqrnjzxwkbgdtqeoylfa
        mphcuisvrnjzxydbgdtqeoylfn
        ophckisvrnjzxwkbgdtqeozlfa
        mphcuisvrkjzxwkbgdtteoblfa
        yphcuisvrnjcxwkbggtqeoylfa
        mphcuisvrnazxwfbqdtqeoylfa
        mphcuisvrmjzxwkbgdtlwoylfa
        mphctksvrnjzxwibgdtqeoylfa
        mphcuisprnjzxlebgdtqeoylfa
        mphcuisnrnjzxakbgdtueoylfa
        mphcuiavrnjoxwtbgdtqeoylfa
        nphcuisvrnjzxwkbgdtqzoylfk
        mphcuisrrnjmxwkbgdtqdoylfa
        mphcuisvrujzxwkvgdtqehylfa
        mphcuisvrnfzxwkogdtqebylfa
        mphcuisvrnjwdwkbgdtqeoyxfa
        mphcuisvrntzxwkrgxtqeoylfa
        mpzcuisvrnjzxwebgdtqeoylsa
        aphcuikvrnjzxwwbgdtqeoylfa
        mphcqisvrnjzxwkpgdtqeoelfa
        mphcuusvrnjzxwkbgdtjeodlfa
        mphcuisvrnjzewkbgdtteoylza
        mphcuisvanjzxwkbgdtheoylfc
        mphcjishrnjzxwkbgltqeoylfa
        mpxcuislrnjzxwkbgdtqeoynfa
        mphcuisvrnjjxwkbgdtmeoxlfa
        mphcimsvrnjzxwkbsdtqeoylfa
        mphcxisvcnjzxwjbgdtqeoylfa
        mphcuisbrvjzxwkbgdtqeoymfa
        mplcuisvrnjzxwkbgdtaenylfa
        mphcuihvrnjzxwkygytqeoylfa
        mphcbisvrnjzxhkbgdtqezylfa
        mphcuisarnjzxwkbgatqeoylfv
        mphcumsvrnjzxwkbgdrqebylfa
        mlhcuisvrnwzxwkbgdtqeoylfx
        mpkcuisvrkjzxwkbgdtqeoylfo
        mphcuissrnjzxwkbgdtqmoylfc
        mphcuiwvrnjuxwkfgdtqeoylfa
        mphcuicvlnjzxwkbgdvqeoylfa
        mphcuisvrvvzxwkbfdtqeoylfa
        myhcuisvrnjpxwkbgntqeoylfa
        mpocuisvrnjzxwtbgitqeoylfa
        mphcuisvrnjzxwkbgdtwewyqfa
        mphcuisvtnjzxwwbgdtqeoolfa
        mphcuisvrnjzxgkbgdyqeoyyfa
        mphcuisvrdjzxwkbgpyqeoylfa
        bphcuisvrnjzxwkbgxtqefylfa
        sphcuisvrdjzxwktgdtqeoylfa
        mphcuvsvrnjmxwobgdtqeoylfa
        mphcuisvrnjzxwkbsdtqeuylfb
        mnhcmisvynjzxwkbgdtqeoylfa
        mphckisvrnjzxwkhgdkqeoylfa
        mpacuisvrnjzxwkbgdtqeoolaa
        mpgcuisvrnjzxwkbzdtqeoynfa
        mphcuisvrojzxwkbzdtqeoylga
        mphcuisvknjfxwkbydtqeoylfa
        mphcuistrnjzxwkbgdqqeuylfa
        bpvcuiszrnjzxwkbgdtqeoylfa
        mphcuxsvrnjzswkbgdtqeoelfa
        mphcuisvbnjzxwlbgdtqeoylla
        mphcuisvonczxwkbgktqeoylfa
        mphcuisvrnkzxwvbgdtquoylfa
        mphcuisvrnjzxokfgdtqeoylia
        tphcuisvrnjzxwkbjdwqeoylfa
        mihcuisvrnjzpwibgdtqeoylfa
        mphcuisvrejzxwkbgdtqjuylfa
        mprcuisvrnjixwkxgdtqeoylfa
        mpqcuiszrnjzxwkbgdtqeodlfa
        mphcuasvrnjzzakbgdtqeoylva
        mphcuisvrnjzmwkbtdtqeoycfa
        mphcuisvrnjzxwkbcdtqioylxa
        mphckisvrnjzxwkbcdtqeoylfm
        mphcuisvrnjuxwbogdtqeoylfa
        mphcuisdrnjzxwkbldtqeoylfx
        mphcuisvrnjoxwkbgdtqeyyyfa
        mphcuicvqnjzxwkbgdtqeoylna
        mpmcuisvrnjzxwkbgdtqktylfa
        mphcuisvrnqzxwkggdtqeoykfa
        mphcuisvryjzxwkbydtqejylfa
        mphcugsvrnjzxwkbghtqeeylfa
        rphcuusvrnjzxwkwgdtqeoylfa
        zphwuiyvrnjzxwkbgdtqeoylfa
        cphcuivvrnjzxwkbgdtqenylfa
        mphcuisvrnjzxwkagotqevylfa
        mprcuisvrcjzxwkbgdtqeoytfa
        mphjugsvrnezxwkbgdtqeoylfa
        mphcuisvryjzxwkbgltqeoylaa
        mphcursvrnjzxfkbgdtqeoydfa
        mphcuisvrcuzxwkbgdtqeoylfw
        mphcuisvrijzxwkbgdtqeoelfh
        xphcuisvenjzxjkbgdtqeoylfa
        mphcuisvrnazxwkbgdeqeoylaa
        mphcuisbrsjzxwkbgdtqeoygfa
        mlhvuisvrnjzxwkbgdtqeoylfh
        mphcuisvrnjzxukbgdtqeoyhfy
        mpzcuilvrnjzawkbgdtqeoylfa
        hphcuisjfnjzxwkbgdtqeoylfa
        mahcuisvrnjzxwkegdtqeoylfi
        mphcuixvrnjzcwkbgdtqetylfa
        mphcuisvrnjzxwkdgdtqeoklfj
        mlhcuisvrnjzxwkbgdteeoylka
        mphcuifvrnjbxwkrgdtqeoylfa
        mphcuasvrnjzzwkbgdtqeoylva
        mphcuisvrnjzxwkboutqeoylba
        mbhcuisvcnjzxwklgdtqeoylfa
        mpbcuisvrnjzxgkbgdtqesylfa
        mphcuisvrnjfswkbgdtqeoylfd
        mphcuisvrnjzxwkbgdoweoysfa
        uphcuisvrnjzrwkbgdtqelylfa
        mphcuisvrnjzxwkbgdtqyoylsi
        mpqcuiqvxnjzxwkbgdtqeoylfa
        mphcuisorfjzxwkbgatqeoylfa
        mphcuisvrntfxwkbzdtqeoylfa
        mphcuisvrnrzxwkbgdtueoylfl
        mphcuisvrnjzewkagdtyeoylfa
        mpocuisdrnjzxwkbgdtqeozlfa
        mphcuisvrnjjxwkbgdtoeoylfm
        mphcuisvenjzxwkbgdtqwoylza
        mpmcuisvrnjzxwkbgdtqeoxlfr
        mphcuisvgnjhxwkbgdtqeoplfa
        mphcuisvrnjzowkdgdtqeoyyfa
        mphcuisqynjzxwkbgdtqeoylda
        hphcuisvgnjzxwkbgdtbeoylfa
        iphcuipvrnuzxwkbgdtqeoylfa
        mphcuisvrnjzsikbpdtqeoylfa
        mpwcuhsvrnjzxbkbgdtqeoylfa
        mnhjuisvcnjzxwkbgdtqeoylfa
        mphcudsvrnjzxwkbgdtqloilfa
        mpncuiwvrwjzxwkbgdtqeoylfa
        mphcuisvrnjgawkbgdtqeoylya
        mphcuisvrnjzxwkbggtteoslfa
        mphcuisvrnjzxwkbgdvqeoylpe
        mphcuisvrnczxfkbgktqeoylfa
        mphcuifvrnjzxwkbgdbmeoylfa
        mphcuisvrnjytwkbgdtqeoylla
        mphcuisvrnjzxwkbgdtjeoxlfn
        mphjuisvrnjzxwkbghtqeoyffa
        mphcuisvrnjzxkrbgdtqeoylaa
        mphcbisvrnjzxwkbgttqeoylfs
        mphkuksvbnjzxwkbgdtqeoylfa
        nphcuidvrnjzxwhbgdtqeoylfa
        mphguzsvrnjzxwkbgdaqeoylfa
        mihcuisfrnjzxwkbgdtqhoylfa
        mphcuisvrnrzxwpbgdtqesylfa
        zphcuisvrnjzxwkbddtqeoylaa
        mphcuigvmnjzxwkbgdtqeoylba
        mjhcuisvrnjzxjkbgdtqeoylha
        mphnuisvrnjznwkbgdtqnoylfa
        mkhcuisvrnjcxwkbgdqqeoylfa
        mphcuisvenjzxwbbqdtqeoylfa
        qphcuisnrnjzawkbgdtqeoylfa
        mphcuisvrdjzxwkbgdtqeoywca
        mphcuzsvvnjzxwfbgdtqeoylfa
        pphcuxsvrnjzxwkbgdtmeoylfa
        mphiuvsvrnjzxlkbgdtqeoylfa
        mphlqisvrnjzxkkbgdtqeoylfa
        mmhcuisvrnjzxwkbgatqeoylea
        mphduisrrnjoxwkbgdtqeoylfa
        mphcuisvrnjnxwkvgdyqeoylfa
        mphcuvsvrnjzxgkbgdtqeoylfz
        mphcuisvryjzxwkbggtqkoylfa
        iphcuisvrdjzxwkbgotqeoylfa
        mphcuisvrnjzxwhbgdtqwoyofa
        mphcorbvrnjzxwkbgdtqeoylfa
        mghcuisvrnpzxykbgdtqeoylfa
        mphauisvrnjnxwkbzdtqeoylfa
        mphcgisvrnjzxwkwgdtqeoygfa
        mphcuisvrnjzxwkggotqeoylba
        mphcuesvrnjzxwkbgdwqebylfa
        yphcuisvrnjzxwkbgdxqeoylja
        ephyuisvrnjzywkbgdtqeoylfa
        mfhcuisqrnjzxwkbgdlqeoylfa
        mphkuisvrnjzxwkbertqeoylfa
        mphcuusgrnjzxwkbggtqeoylfa
        mphcuildrnjvxwkbgdtqeoylfa
        mphcuiuvrnjzlwkbgwtqeoylfa
        mppcuisvrljzxwkbgdtqeoylfw
        mphcwiwvrnjzxwsbgdtqeoylfa
        mphcubivrnjzxwkqgdtqeoylfa
        mphcuisvrnjpxwkngdtqeoylpa
        pchcuisvrgjzxwkbgdtqeoylfa
        mphcuisvlnjzxwkbgdtmeoylfw
        mphcuisvrnjzywkbgdvqeoylfj
        mpzcuisvrnezxwktgdtqeoylfa
        mphcuisvrnjbxwkbgzrqeoylfa
        mphcuisvrnjzxwktgdtqeodtfa
        jphcuiavrnjzxwkbgdtqeoylfv
        mphcuisvrnjzxwkbddppeoylfa
        mphcuissrkjzxwkbgxtqeoylfa
        mphcuisvrhjzxwxbgdtqeoylxa
        mphcvisvgnjjxwkbgdtqeoylfa
        mphcuisprnjwxwtbgdtqeoylfa
        mphcuissrnjzxqkbgdtqeoymfa
        mphcuiabrnjzxokbgdtqeoylfa
        mphcuisvrnczxwkbgmtpeoylfa'''.stripIndent()
}
