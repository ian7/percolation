import org.junit.experimental.categories.Category
import spock.lang.Shared

class PercolationTest extends spock.lang.Specification {
    def "SizeOutOfRange"() {
        when:
        def p2 = new Percolation(0)
        then:
        thrown(IllegalArgumentException)
    }

    def "Open"() {
        given:
        def p1 = new Percolation(3)
        expect:
        p1.isOpen(1, 1) == false
    }

    def "IsOpenOutOfRange"() {
        given:
        def p1 = new Percolation(3)
        when:
        p1.isOpen(x, y)

        then:
        def error = thrown(expectedException)

        where:
        x | y || expectedException
        0 | 1 || IllegalArgumentException
        1 | 0 || IllegalArgumentException
        0 | 0 || IllegalArgumentException
        4 | 1 || IllegalArgumentException
        1 | 4 || IllegalArgumentException
        4 | 4 || IllegalArgumentException
    }

    def "IsOpen"() {
    }

    def "IsFull"() {
    }

    def "NumberOfOpenSites"() {
    }

    def "Percolates"() {
    }

    def "TwoByTwoPercolationClosed"() {
        given:
        def p3 = new Percolation(3);
        expect:
        p3.percolates() == false
    }

    def "ThreeByThreePercolationOpen"() {
        given:
        def p3 = new Percolation(3);
        p3.open(1, 1)
        p3.open(2, 1)
        p3.open(3, 1)
        expect:
        p3.percolates() == true
        p3.numberOfOpenSites() == 3
    }

    def "ThreeByThreePercolationDiagnoal"() {
        given:
        def p3 = new Percolation(3);
        p3.open(1, 1)
        p3.open(2, 2)
        p3.open(3, 3)
        expect:
        p3.percolates() == false
        p3.numberOfOpenSites() == 3
    }

    def "ThreeByThreePercolationGap"() {
        given:
        def p3 = new Percolation(3);
        p3.open(1, 1)
        p3.open(1, 3)
        expect:
        p3.percolates() == false
        p3.numberOfOpenSites() == 2
    }

    def "TwoByTwoPercolationLine"() {
        given:
        def p3 = new Percolation(2);
        p3.open(1, 1)
        p3.open(2, 1)
        expect:
        p3.percolates() == true
        p3.numberOfOpenSites() == 2
    }

    def "TwoByTwoPercolationDiagonal"() {
        given:
        def p3 = new Percolation(2);
        p3.open(1, 1)
        p3.open(2, 2)
        expect:
        p3.percolates() == false
        p3.numberOfOpenSites() == 2
    }

    def "FourPercolationDiagonal"() {
        given:
        def p3 = new Percolation(4);
        p3.open(1, 1)
        p3.open(2, 1)
        p3.open(2, 1)
        p3.open(2, 1)
        expect:
        p3.percolates() == false
        p3.numberOfOpenSites() == 2
    }

}
