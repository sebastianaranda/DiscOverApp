package com.digitalhouse.a0819cpmoacn02armo_01.model;

import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ArtistDao {

    public void getArtistsFromApi(ResultListener<List<Artist>> controllerListener) {
        List<Artist> artists = new ArrayList<>();
        //TODO: (Juan) Borrar cuando venga la data de la API
        artists.add(new Artist(0, "Mudvayne", 4,R.drawable.img_artist_placeholder,"1996 - 2010","Mudvayne fue una banda estadounidense de heavy metal formada en Peoria, Illinois en 1996. Consta de cuatro miembros..."));
        artists.add(new Artist(0, "Tool", 4,R.drawable.img_artist_placeholder,"1990 - Presente","Tool es una banda estadounidense de metal progresivo surgida en 1990 en Los Ángeles, California. Ha vendido más de..."));
        artists.add(new Artist(0, "Kidney Thieves", 4,R.drawable.img_artist_placeholder,"1998 - Presente","Kidneythieves es una banda estadounidense de rock industrial, fundada en 1998, dirigida por Free Dominguez..."));
        artists.add(new Artist(0, "The Bangles", 7,R.drawable.img_artist_placeholder,"1981 - Presente","The Bangles es uno de los primeros grupos formados exclusivamente por mujeres con una alineación funcional..."));
        artists.add(new Artist(0, "Black Sabbath", 13,R.drawable.img_artist_placeholder,"1968 - 2017","Black Sabbath es una banda británica de heavy metal y de hard rock formada en 1968\u200B en Birmingham por..."));
        artists.add(new Artist(0, "Dir en Grey", 12,R.drawable.img_artist_placeholder,"1997 - Presente","Dir En Grey Es una banda japonesa de metal experimental, \u200B formada en 1997 por los ex miembros de..."));
        artists.add(new Artist(0, "L'arc en ciel", 9,R.drawable.img_artist_placeholder,"1991 - Presente","L'Arc~en~Ciel, también conocido por sus seguidores como Laruku o L'Arc, es una banda de rock japonés..."));
        artists.add(new Artist(0, "Tori Amos", 5,R.drawable.img_artist_placeholder,"1988 - Presente","Myra Ellen Amos, más conocida por su nombre artístico Tori Amos, es una pianista y cantautora estadounidense..."));
        artists.add(new Artist(0, "Judas Priest", 15,R.drawable.img_artist_placeholder,"1969 - Presente","Judas Priest es una banda británica de heavy metal fundada en 1969 en Birmingham, Inglaterra. Su alineación..."));
        artists.add(new Artist(0, "Portishead", 3,R.drawable.img_artist_placeholder,"1991 - Presente","Portishead es el nombre de un grupo musical británico, de tendencia trip hop, formado en Brístol en 1991."));
        artists.add(new Artist(0, "Jack Off Jill", 2,R.drawable.img_artist_placeholder,"1992 - 2015","Jack Off Jill fue una banda estadounidense de Riot grrrl originaria de Florida activa entre 1992 y el..."));
        artists.add(new Artist(0, "King Crimson", 8,R.drawable.img_artist_placeholder,"1969 - Presente","King Crimson es una banda de rock progresivo fundada en Londres en 1969. Es considerada una de las..."));
        artists.add(new Artist(0, "Jinjer", 3,R.drawable.img_artist_placeholder,"2009 - Presente","Jinjer es una banda ucraniana de groove metal y metal progresivo, formada en el año 2009.\u200B Desde su..."));
        artists.add(new Artist(0,"Muse",7, R.drawable.img_artist_placeholder,"1994 - Presente","Muse es una banda británica de rock alternativo formada en 1994 en Inglaterra. Desde su formación en la..."));
        artists.add(new Artist(0, "Mudvayne", 4,R.drawable.img_artist_placeholder,"1996 - 2010","Mudvayne fue una banda estadounidense de heavy metal formada en Peoria, Illinois en 1996. Consta de cuatro miembros..."));
        artists.add(new Artist(0, "Tool", 4,R.drawable.img_artist_placeholder,"1990 - Presente","Tool es una banda estadounidense de metal progresivo surgida en 1990 en Los Ángeles, California. Ha vendido más de..."));
        artists.add(new Artist(0, "Kidney Thieves", 4,R.drawable.img_artist_placeholder,"1998 - Presente","Kidneythieves es una banda estadounidense de rock industrial, fundada en 1998, dirigida por Free Dominguez..."));
        artists.add(new Artist(0, "The Bangles", 7,R.drawable.img_artist_placeholder,"1981 - Presente","The Bangles es uno de los primeros grupos formados exclusivamente por mujeres con una alineación funcional..."));
        artists.add(new Artist(0, "Black Sabbath", 13,R.drawable.img_artist_placeholder,"1968 - 2017","Black Sabbath es una banda británica de heavy metal y de hard rock formada en 1968\u200B en Birmingham por..."));
        artists.add(new Artist(0, "Dir en Grey", 12,R.drawable.img_artist_placeholder,"1997 - Presente","Dir En Grey Es una banda japonesa de metal experimental, \u200B formada en 1997 por los ex miembros de..."));
        artists.add(new Artist(0, "L'arc en ciel", 9,R.drawable.img_artist_placeholder,"1991 - Presente","L'Arc~en~Ciel, también conocido por sus seguidores como Laruku o L'Arc, es una banda de rock japonés..."));
        artists.add(new Artist(0, "Tori Amos", 5,R.drawable.img_artist_placeholder,"1988 - Presente","Myra Ellen Amos, más conocida por su nombre artístico Tori Amos, es una pianista y cantautora estadounidense..."));
        artists.add(new Artist(0, "Judas Priest", 15,R.drawable.img_artist_placeholder,"1969 - Presente","Judas Priest es una banda británica de heavy metal fundada en 1969 en Birmingham, Inglaterra. Su alineación..."));
        artists.add(new Artist(0, "Portishead", 3,R.drawable.img_artist_placeholder,"1991 - Presente","Portishead es el nombre de un grupo musical británico, de tendencia trip hop, formado en Brístol en 1991."));
        artists.add(new Artist(0, "Jack Off Jill", 2,R.drawable.img_artist_placeholder,"1992 - 2015","Jack Off Jill fue una banda estadounidense de Riot grrrl originaria de Florida activa entre 1992 y el..."));
        artists.add(new Artist(0, "King Crimson", 8,R.drawable.img_artist_placeholder,"1969 - Presente","King Crimson es una banda de rock progresivo fundada en Londres en 1969. Es considerada una de las..."));
        artists.add(new Artist(0, "Jinjer", 3,R.drawable.img_artist_placeholder,"2009 - Presente","Jinjer es una banda ucraniana de groove metal y metal progresivo, formada en el año 2009.\u200B Desde su..."));
        artists.add(new Artist(0,"Muse",7, R.drawable.img_artist_placeholder,"1994 - Presente","Muse es una banda británica de rock alternativo formada en 1994 en Inglaterra. Desde su formación en la..."));

        controllerListener.finish(artists);
    }
}
