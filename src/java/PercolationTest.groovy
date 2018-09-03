import spock.lang.Shared

class PercolationTest extends spock.lang.Specification {

    @Shared p1 = new Percolation(1)
    def "Open"() {
        expect:
        p1.isOpen(0,0) == false
    }

    def "IsOpen"() {
    }

    def "IsFull"() {
    }

    def "NumberOfOpenSites"() {
    }

    def "Percolates"() {
    }
}
