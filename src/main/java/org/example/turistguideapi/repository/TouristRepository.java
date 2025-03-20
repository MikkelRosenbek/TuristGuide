package org.example.turistguideapi.repository;

import org.example.turistguideapi.model.Location;
import org.example.turistguideapi.model.Tag;
import org.example.turistguideapi.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouristRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TouristAttraction> getAttractions(){
        String sql = """
                SELECT Attractions.AttractionID, Attractions.Name, Attractions.Description,Locations.LocationName AS LocationName
                FROM Attractions
                JOIN Locations ON Attractions.LocationID = Locations.LocationID
                """;
        RowMapper<TouristAttraction> rowMapper = (rs, rowNum) -> new TouristAttraction(
                rs.getString("Name"),
                rs.getString("Description"),
                Location.valueOf(rs.getString("LocationName").trim().toUpperCase()),
                attractionsTag(rs.getInt("AttractionID"))
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

    public TouristAttraction getAttractionByName(String name) {
        String sql = """
                SELECT Attractions.AttractionID, Attractions.Name, Attractions.Description, Locations.LocationName AS LocationName
                FROM Attractions
                JOIN Locations ON Attractions.LocationID = Locations.LocationID
                WHERE Attractions.Name = ?
                """;
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> new TouristAttraction(
                rs.getString("Name"),
                rs.getString("Description"),
                Location.valueOf(rs.getString("LocationName").trim().toUpperCase()),
                attractionsTag(rs.getInt("AttractionID"))
        ));
    }

    public void insertAttraction(TouristAttraction touristAttraction){
        String sql = """
                INSERT INTO Attractions (Name,Description,LocationID)
                VALUES (?,?, (SELECT LocationID FROM Locations WHERE Name = ?))
                ON DUPLICATE KEY UPDATE
                Description = VALUES(Description),
                LocationID = VALUES(LocationID)
                """;
        jdbcTemplate.update(sql, touristAttraction.getName(), touristAttraction.getDescription(), touristAttraction.getLokation());
    }

    public void updateAttraction(TouristAttraction updatedAttraction){
        String sql = """
                UPDATE Attractions 
                SET Description = ?, LocationID = (SELECT LocationID FROM Locations WHERE Name = ?)
                WHERE Name = ?
                """;
        jdbcTemplate.update(sql, updatedAttraction.getDescription(), updatedAttraction.getLokation(), updatedAttraction.getName());

        String deleteSQL = """
                DELETE FROM AttractionTag
                WHERE AttractionID = (SELECT AttractionID FROM Attractions WHERE Name = ?)
                """;
        jdbcTemplate.update(deleteSQL, updatedAttraction.getName());

        String insertNewTag = """
                INSERT INTO AttractionTag (AttractionID, TagID)
                VALUES (
                (SELECT AttractionID FROM Attractions WHERE Name = ?),
                (SELECT TagID FROM Tags WHERE TagName = ?)
                )
                """;
        for (Tag tag : updatedAttraction.getTags()){
            jdbcTemplate.update(insertNewTag, updatedAttraction.getName(), tag.getDisplayName());
        }
    }

    public TouristAttraction deleteAttraction(TouristAttraction touristAttraction){
        TouristAttraction deleteAttraction = getAttractionByName(touristAttraction.getName());
        if (deleteAttraction != null){
            String sql = "DELETE FROM Attractions WHERE Name = ?";
            jdbcTemplate.update(sql, touristAttraction.getName());
        }
        return deleteAttraction;
    }

    private List<Tag> attractionsTag(int attractionID){
        String sql = """
                SELECT Tags.TagName
                FROM Tags
                JOIN AttractionTag ON Tags.TagID = AttractionTag.TagID
                WHERE AttractionTag.AttractionID = ?
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> Tag.valueOf(rs.getString("TagName")), attractionID);
    }
}
