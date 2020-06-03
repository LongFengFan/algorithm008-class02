
package Week_07;

/**
 * 并查集
 */
public class UnionFind {
    private int count = 0;
    private int[] parent;
    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    public int find(int p) {
        while (p != parent[p]) {
//            路径压缩，不需要也是可以的，不过有了这句下次再找就可以O(1)时间
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(10);
        System.out.println(unionFind.count);
        unionFind.union(1,2);
        unionFind.union(1,3);
        System.out.println(unionFind.find(1));
    }

}
