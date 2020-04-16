package ro.uaic.info.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ChartController {
    public static void create(String name, List<String> albums) throws DatabaseException {
        var insertChart = "insert into charts(name) values(?)";
        var insertChartContent = "insert into charts_content(chart_id, position, album_id) values(?, ?, ?)";

        try {
            var stmt = Database.getConnection().prepareStatement(insertChart);
            stmt.setString(1, name);

            stmt.execute();

            var chartId = ChartController.findByName(name).getId();

            for (int i = 0; i < albums.size(); ++i) {
                stmt = Database.getConnection().prepareStatement(insertChartContent);
                stmt.setInt(1, chartId);
                stmt.setInt(2, i + 1);
                stmt.setInt(3, AlbumController.findByName(albums.get(i)).getId());

                stmt.execute();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static ChartDAO findByName(String name) throws DatabaseException {
        var selectChart = "select id, name from charts where name = ?";
        var selectChartContent = "select id, album_id, position from charts_content where chart_id = ? order by position asc";

        try {
            var selectChartStmt = Database.getConnection().prepareStatement(selectChart);
            selectChartStmt.setString(1, name);

            var selectChartRs = selectChartStmt.executeQuery();
            if (!selectChartRs.next()) return null;

            var albums = new LinkedList<AlbumDAO>();
            var selectChartContentStmt = Database.getConnection().prepareStatement(selectChartContent);
            selectChartContentStmt.setInt(1, selectChartRs.getInt(1));
            var selectChartContentRs = selectChartContentStmt.executeQuery();

            while (selectChartContentRs.next())
                albums.add(AlbumController.findById(selectChartContentRs.getInt(2)));

            return new ChartDAO(selectChartRs.getInt(1), selectChartRs.getString(2), albums);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
