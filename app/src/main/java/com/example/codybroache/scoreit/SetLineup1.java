package com.example.codybroache.scoreit;

        import android.app.Activity;
        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Random;
        import java.util.zip.GZIPInputStream;

/**
 * Created by Brandon on 11/6/2015.
 */
public class SetLineup1 extends Activity {
    private static String[] awayLineup = new String[9];
    private static String pitcher;
    private static TextView teamName;
    private final static String ACCESS_TOKEN = "adfeb89d-a32f-4bc5-9911-834bc714a9f3";
    private static PlayerListAdapter mAdapterAway;
    private static List<Player> awayList;
    private static ArrayList<AutoCompleteTextView> awayAdapters = new ArrayList<AutoCompleteTextView>();
    private static ArrayList<AutoCompleteTextView> awayPosAdapters = new ArrayList<AutoCompleteTextView>();
    private static ArrayList<String> arr = new ArrayList<>();
    private static String[] arr2 = new String[]{"P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF", "DH"};
    private static ArrayAdapter<String> adapter;
    private static AutoCompleteTextView awayPlayer1;
    private static AutoCompleteTextView awayPlayer2;
    private static AutoCompleteTextView awayPlayer3;
    private static AutoCompleteTextView awayPlayer4;
    private static AutoCompleteTextView awayPlayer5;
    private static AutoCompleteTextView awayPlayer6;
    private static AutoCompleteTextView awayPlayer7;
    private static AutoCompleteTextView awayPlayer8;
    private static AutoCompleteTextView awayPlayer9;
    private static AutoCompleteTextView awayPlayer10;
    private static TextView awayNumber1;
    private static TextView awayNumber2;
    private static TextView awayNumber3;
    private static TextView awayNumber4;
    private static TextView awayNumber5;
    private static TextView awayNumber6;
    private static TextView awayNumber7;
    private static TextView awayNumber8;
    private static TextView awayNumber9;
    private static TextView awayNumber10;
    private static ArrayAdapter<String> adapterPos;
    private static AutoCompleteTextView awayPosition1;
    private static AutoCompleteTextView awayPosition2;
    private static AutoCompleteTextView awayPosition3;
    private static AutoCompleteTextView awayPosition4;
    private static AutoCompleteTextView awayPosition5;
    private static AutoCompleteTextView awayPosition6;
    private static AutoCompleteTextView awayPosition7;
    private static AutoCompleteTextView awayPosition8;
    private static AutoCompleteTextView awayPosition9;
    private static AutoCompleteTextView awayPosition10;

    private static Button nextTeam;
    private static HashMap<String, Integer> awayNamesAndNumbers = new HashMap<String, Integer>();

    String username;
    static private final String TAG_USER = "scoreit_username";

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_set_lineup1);

        Intent intent = getIntent();
        username = intent.getExtras().getString(TAG_USER);

        mAdapterAway = new PlayerListAdapter(getApplicationContext());

        teamName = (TextView) findViewById(R.id.team_name);
        teamName.setText(getIntent().getStringExtra("Away Team"));

        new FindLineups().execute(teamName.getText().toString().toLowerCase().replace(' ', '-'));

        adapter = new ArrayAdapter<String>(this, R.layout.dropdown_player, arr);
        adapterPos = new ArrayAdapter<String>(this, R.layout.dropdown_player, arr2);

        awayPlayer1 = (AutoCompleteTextView) findViewById(R.id.away_name_1);
        awayNumber1 = (TextView) findViewById(R.id.away_num_1);
        awayPosition1 = (AutoCompleteTextView) findViewById(R.id.away_pos_1);
        awayAdapters.add(awayPlayer1);
        awayPosAdapters.add(awayPosition1);
        awayPlayer2 = (AutoCompleteTextView) findViewById(R.id.away_name_2);
        awayNumber2 = (TextView) findViewById(R.id.away_num_2);
        awayPosition2 = (AutoCompleteTextView) findViewById(R.id.away_pos_2);
        awayAdapters.add(awayPlayer2);
        awayPosAdapters.add(awayPosition2);
        awayPlayer3 = (AutoCompleteTextView) findViewById(R.id.away_name_3);
        awayNumber3 = (TextView) findViewById(R.id.away_num_3);
        awayPosition3 = (AutoCompleteTextView) findViewById(R.id.away_pos_3);
        awayAdapters.add(awayPlayer3);
        awayPosAdapters.add(awayPosition3);
        awayPlayer4 = (AutoCompleteTextView) findViewById(R.id.away_name_4);
        awayNumber4 = (TextView) findViewById(R.id.away_num_4);
        awayPosition4 = (AutoCompleteTextView) findViewById(R.id.away_pos_4);
        awayAdapters.add(awayPlayer4);
        awayPosAdapters.add(awayPosition4);
        awayPlayer5 = (AutoCompleteTextView) findViewById(R.id.away_name_5);
        awayNumber5 = (TextView) findViewById(R.id.away_num_5);
        awayPosition5 = (AutoCompleteTextView) findViewById(R.id.away_pos_5);
        awayAdapters.add(awayPlayer5);
        awayPosAdapters.add(awayPosition5);
        awayPlayer6 = (AutoCompleteTextView) findViewById(R.id.away_name_6);
        awayNumber6 = (TextView) findViewById(R.id.away_num_6);
        awayPosition6 = (AutoCompleteTextView) findViewById(R.id.away_pos_6);
        awayAdapters.add(awayPlayer6);
        awayPosAdapters.add(awayPosition6);
        awayPlayer7 = (AutoCompleteTextView) findViewById(R.id.away_name_7);
        awayNumber7 = (TextView) findViewById(R.id.away_num_7);
        awayPosition7 = (AutoCompleteTextView) findViewById(R.id.away_pos_7);
        awayAdapters.add(awayPlayer7);
        awayPosAdapters.add(awayPosition7);
        awayPlayer8 = (AutoCompleteTextView) findViewById(R.id.away_name_8);
        awayNumber8 = (TextView) findViewById(R.id.away_num_8);
        awayPosition8 = (AutoCompleteTextView) findViewById(R.id.away_pos_8);
        awayAdapters.add(awayPlayer8);
        awayPosAdapters.add(awayPosition8);
        awayPlayer9 = (AutoCompleteTextView) findViewById(R.id.away_name_9);
        awayNumber9 = (TextView) findViewById(R.id.away_num_9);
        awayPosition9 = (AutoCompleteTextView) findViewById(R.id.away_pos_9);
        awayAdapters.add(awayPlayer9);
        awayPosAdapters.add(awayPosition9);
        awayPlayer10 = (AutoCompleteTextView) findViewById(R.id.away_pitcher);
        awayNumber10 = (TextView) findViewById(R.id.away_pitcher_number);
        awayAdapters.add(awayPlayer10);
        for (final AutoCompleteTextView p : awayPosAdapters) {
            p.setThreshold(0);
            p.setAdapter(adapterPos);
            p.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (AutoCompleteTextView q : awayPosAdapters) {
                        if (q.getText().toString().equals(p.getText().toString()) &&
                                awayPosAdapters.indexOf(q) != awayPosAdapters.indexOf(p)) {
                            p.setText("");
                            Toast.makeText(SetLineup1.this, "You cannot enter the same position twice.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (q.getText().toString().equals("DH") && p.getText().toString().equals("P")
                                || p.getText().toString().equals("DH") && q.getText().toString().equals("P")) {
                            p.setText("");
                            Toast.makeText(SetLineup1.this, "You cannot have a DH and a P.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            p.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String str = p.getText().toString();
                        if (str.equals("")) {
                            return;
                        }
                        int count = arr2.length;
                        for (int i = 0; i < count; i++) {
                            String temp = arr2[i];
                            if (str.compareTo(temp) == 0) {
                                for (final AutoCompleteTextView q : awayPosAdapters) {
                                    if (q.getText().toString().equals(p.getText().toString()) &&
                                            awayPosAdapters.indexOf(q) != awayPosAdapters.indexOf(p)) {
                                        p.setText("");
                                        Toast.makeText(SetLineup1.this, "You cannot enter the same " +
                                                "position twice.", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                                return;
                            }
                        }
                        p.setText("");
                        Toast.makeText(SetLineup1.this, "That was not a valid position. Choose " +
                                "from the autocomplete suggestions.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        for (final AutoCompleteTextView p : awayAdapters) {
            p.setThreshold(1);
            p.setAdapter(adapter);
            p.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (AutoCompleteTextView q : awayAdapters) {
                        if (q.getText().toString().equals(p.getText().toString()) &&
                                awayAdapters.indexOf(q) != awayAdapters.indexOf(p) &&
                                awayAdapters.indexOf(q) != 9 && awayAdapters.indexOf(p) != 9) {
                            p.setText("");
                            Toast.makeText(SetLineup1.this, "You cannot enter the same player twice.",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    int index = awayAdapters.indexOf(p);
                    setNumberText(index, awayNamesAndNumbers.get(p.getText().toString()) + "");
                }
            });
            p.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // on focus off
                        String str = p.getText().toString();
                        int index = awayAdapters.indexOf(p);
                        if (str.equals("")) {
                            setNumberText(index, "");
                            return;
                        }
                        int count = arr.size();
                        for (int i = 0; i < count; i++) {
                            String temp = arr.get(i);
                            if (str.compareTo(temp) == 0) {
                                for (final AutoCompleteTextView q : awayAdapters) {
                                    if (q.getText().toString().equals(p.getText().toString()) &&
                                            awayAdapters.indexOf(q) != awayAdapters.indexOf(p) &&
                                            awayAdapters.indexOf(q) != 9 && awayAdapters.indexOf(p) != 9) {
                                        p.setText("");
                                        setNumberText(index, "");
                                        Toast.makeText(SetLineup1.this, "You cannot enter the same player" +
                                                " twice.", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                                setNumberText(index, awayNamesAndNumbers.get(p.getText().toString())
                                        + "");
                                return;
                            }
                        }
                        p.setText("");
                        setNumberText(index, "");
                        Toast.makeText(SetLineup1.this, "That was not a valid player. Choose from " +
                                "the autocomplete suggestions.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        nextTeam = (Button) findViewById(R.id.next_team);
        nextTeam.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        int check = checkAll();
                        if (check == 0) {
                            Intent intent = new Intent(SetLineup1.this, SetLineup2.class);
                            intent.putExtra("Home Team", getIntent().getStringExtra("Home Team"));
                            intent.putExtra(TAG_USER, username);
                            startActivity(intent);
                        } else if (check < 3) {
                            Toast.makeText(SetLineup1.this, "Please fill in all information before " +
                                    "continuing.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SetLineup1.this, "The player with position 'P' must equal " +
                                    "the pitcher at the bottom.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    private int checkAll() {
        for (final AutoCompleteTextView p : awayAdapters) {
            if (p.getText().toString().equals("")) {
                return 1;
            }
        }
        for (final AutoCompleteTextView p : awayPosAdapters) {
            if (p.getText().toString().equals("")) {
                return 2;
            } else if (p.getText().toString().equals("P")) {
                int index = awayPosAdapters.indexOf(p);
                if (!awayAdapters.get(index).getText().toString().equals(awayPlayer10.getText().toString())) {
                    return 3;
                }
            }
        }
        return 0;
    }

    private void setNumberText(int index, String text) {
        if (index == 0) {
            awayNumber1.setText(text + "");
        } else if (index == 1) {
            awayNumber2.setText(text + "");
        } else if (index == 2) {
            awayNumber3.setText(text + "");
        } else if (index == 3) {
            awayNumber4.setText(text + "");
        } else if (index == 4) {
            awayNumber5.setText(text + "");
        } else if (index == 5) {
            awayNumber6.setText(text + "");
        } else if (index == 6) {
            awayNumber7.setText(text + "");
        } else if (index == 7) {
            awayNumber8.setText(text + "");
        } else if (index == 8) {
            awayNumber9.setText(text + "");
        } else {
            awayNumber10.setText(text + "");
        }
    }

    public class FindLineups extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                return gatherInformation(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            boolean unique;
            List<Player> away = mAdapterAway.getItems();
            for (Player p : away) {
                while (p.getNumber() == -1) {
                    unique = true;
                    int newNum = new Random().nextInt(99) + 1;
                    for (Player q : away) {
                        if (newNum == q.getNumber()) {
                            unique = false;
                        }
                    }
                    if (unique) {
                        p.setNumber(newNum);
                    }
                }
            }
            mAdapterAway.notifyDataSetChanged();

            awayList = mAdapterAway.getItems();
            for (Player p : awayList) {
                awayNamesAndNumbers.put(p.getFirstName() + " " + p.getLastName(), p.getNumber());
                arr.add(p.getFirstName() + " " + p.getLastName());
            }
        }
    }

    private static boolean gatherInformation(String team) throws IOException {
        boolean result = doTeam(team);
        return result;
    }

    private static boolean doTeam(String team) throws IOException {
        HttpURLConnection connection = null;

        try {
            URL url = new URL("https://erikberg.com/mlb/roster/" + team + ".json");

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-agent", "brandon.weber@yahoo.com");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
            connection.setRequestProperty("Content-length", "0");
            connection.setRequestProperty("Accept-encoding", "gzip");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.connect();

            InputStream in = connection.getInputStream();
            String encoding = connection.getContentEncoding();

            StringBuilder sb = new StringBuilder();

            // Verify the response is compressed, before attempting to decompress it
            try {
                if ("gzip".equals(encoding)) {
                    in = new GZIPInputStream(in);
                }
            } catch (IOException ex) {
                System.err.println("Error trying to read gzip data.");
                ex.printStackTrace();
                System.exit(1);
            }

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException ex) {
                System.err.println("Error reading response.");
                ex.printStackTrace();
                System.exit(1);
            }

            String result = sb.toString();
            if (result != null) {
                // Have the data we want, now call function to parse it
                String abbrev = result.substring(result.indexOf("abbreviation") + 15, result.indexOf("\"", result.indexOf("abbreviation") + 15));
                result = result.substring(result.indexOf("players"));
                int i = 0;
                while (result.contains("last_name")) {
                    result = result.substring(result.indexOf("last_name") + 12);
                    String last = result.substring(0, result.indexOf('\"'));
                    String temp = result.substring(result.indexOf("display_name") + 15, result.indexOf("birthdate") - 3);
                    String first = temp.substring(0, temp.length() - last.length() - 1);
                    int num = Integer.valueOf(result.substring(result.indexOf("uniform_number") + 16,
                            result.indexOf(',', result.indexOf("uniform_number") + 16)));
                    Player p = new Player(first, last, num);
                    mAdapterAway.add(p);
                }
            }

            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return false;
    }
}
