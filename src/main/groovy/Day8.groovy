class Day8 {

    static int idx
    static int[] ints

    static def test1(String input) {
        Node root = readIntoTree(input)
        root.sumMetadatas()
    }

    static def test2(String input) {
        Node root = readIntoTree(input)
        root.sumReferenceMetadatas()
    }

    private static Node readIntoTree(String input) {
        ints = input.split(/\s+/).collect { it.toInteger() }
        idx = 0
        Node root = readSingleNode()
        root
    }

    static Node readSingleNode() {
        def node = new Node(idx: idx)
        def numChildren = ints[idx]
        def numMds = ints[idx+1]
        idx += 2
        node.children = readChildren numChildren
        node.metadatas = readMetadatas numMds
        node
    }

    static List<Node> readChildren(int numChildren) {
        def children = []
        numChildren.times {
            children << readSingleNode()
        }
        children
    }

    static int[] readMetadatas(def numMetadatas) {
        if (numMetadatas == 0) return []
        def baseIdx = idx
        idx += numMetadatas
        ints[baseIdx..idx-1]
    }

    static class Node {
        int idx
        Node[] children
        int[] metadatas

        int sumMetadatas() {
            def childrenMd = children*.sumMetadatas()?.sum() ?: 0
            childrenMd + (metadatas.sum()?: 0)
        }

        int sumReferenceMetadatas() {
            if (! children) {
                return sumMetadatas()
            }
            metadatas.collect {
                if (it - 1 in (0..children.size()-1)) {
                    children[it-1].sumReferenceMetadatas()
                } else {
                    0
                }
            }.sum()?: 0
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("idx=").append(idx)
            sb.append(", children=").append(children?.size());
            sb.append(", metadatas=").append(metadatas?.size());
            sb.append('}');
            return sb.toString();
        }
    }
}
