import java.util.*;

class SQL {

    class Table {
        int cols;
        int nextId;
        Map<Integer, List<String>> rows;

        Table(int cols) {
            this.cols = cols;
            this.nextId = 1;
            this.rows = new HashMap<>();
        }
    }

    Map<String, Table> tables;

    public SQL(List<String> names, List<Integer> columns) {
        tables = new HashMap<>();

        for (int i = 0; i < names.size(); i++) {
            tables.put(names.get(i), new Table(columns.get(i)));
        }
    }

    public boolean ins(String name, List<String> row) {
        if (!tables.containsKey(name))
            return false;

        Table t = tables.get(name);

        if (row.size() != t.cols)
            return false;

        t.rows.put(t.nextId, new ArrayList<>(row));
        t.nextId++;

        return true;
    }

    public void rmv(String name, int rowId) {
        if (!tables.containsKey(name))
            return;

        tables.get(name).rows.remove(rowId);
    }

    public String sel(String name, int rowId, int columnId) {
        if (!tables.containsKey(name))
            return "<null>";

        Table t = tables.get(name);

        if (!t.rows.containsKey(rowId))
            return "<null>";

        if (columnId < 1 || columnId > t.cols)
            return "<null>";

        return t.rows.get(rowId).get(columnId - 1);
    }

    public List<String> exp(String name) {
        List<String> ans = new ArrayList<>();

        if (!tables.containsKey(name))
            return ans;

        Table t = tables.get(name);

        List<Integer> ids = new ArrayList<>(t.rows.keySet());
        Collections.sort(ids);

        for (int id : ids) {
            StringBuilder sb = new StringBuilder();
            sb.append(id);

            for (String s : t.rows.get(id)) {
                sb.append(",").append(s);
            }

            ans.add(sb.toString());
        }

        return ans;
    }
}

