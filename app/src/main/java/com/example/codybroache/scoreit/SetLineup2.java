package com.example.codybroache.scoreit;

        import android.app.Activity;
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
 * Created by Brandon on 11/20/2015.
 */
public class SetLineup2 extends Activity {
    private static Bundle savedInstanceState1;
    private static Player[] homeLineup = new Player[9];
    private static TextView teamName;
    private final static String ACCESS_TOKEN = "adfeb89d-a32f-4bc5-9911-834bc714a9f3";
    private static PlayerListAdapter mAdapterHome;
    private static List<Player> homeList;
    private static ArrayList<AutoCompleteTextView> homeAdapters = new ArrayList<AutoCompleteTextView>();
    private static ArrayList<AutoCompleteTextView> homePosAdapters = new ArrayList<AutoCompleteTextView>();
    private static ArrayList<String> arr = new ArrayList<>();
    private static String[] arr2 = new String[]{"P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF", "DH"};
    private static ArrayAdapter<String> adapter;
    private static ArrayAdapter<String> adapterPos;
    private static AutoCompleteTextView homePlayer1;
    private static AutoCompleteTextView homePlayer2;
    private static AutoCompleteTextView homePlayer3;
    private static AutoCompleteTextView homePlayer4;
    private static AutoCompleteTextView homePlayer5;
    private static AutoCompleteTextView homePlayer6;
    private static AutoCompleteTextView homePlayer7;
    private static AutoCompleteTextView homePlayer8;
    private static AutoCompleteTextView homePlayer9;
    private static AutoCompleteTextView homePlayer10;
    private static TextView homeNumber1;
    private static TextView homeNumber2;
    private static TextView homeNumber3;
    private static TextView homeNumber4;
    private static TextView homeNumber5;
    private static TextView homeNumber6;
    private static TextView homeNumber7;
    private static TextView homeNumber8;
    private static TextView homeNumber9;
    private static TextView homeNumber10;
    private static AutoCompleteTextView homePosition1;
    private static AutoCompleteTextView homePosition2;
    private static AutoCompleteTextView homePosition3;
    private static AutoCompleteTextView homePosition4;
    private static AutoCompleteTextView homePosition5;
    private static AutoCompleteTextView homePosition6;
    private static AutoCompleteTextView homePosition7;
    private static AutoCompleteTextView homePosition8;
    private static AutoCompleteTextView homePosition9;
    private static Button nextTeam;
    private static HashMap<String, Integer> homeNamesAndNumbers = new HashMap<String, Integer>();

    String username;
    static private final String TAG_USER = "scoreit_username";

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_set_lineup2);

        Intent intent = getIntent();
        username = intent.getExtras().getString(TAG_USER);

        mAdapterHome = new PlayerListAdapter(getApplicationContext());
        teamName = (TextView) findViewById(R.id.team_name_2);
        homePlayer1 = (AutoCompleteTextView) findViewById(R.id.home_name_1);
        homeNumber1 = (TextView) findViewById(R.id.home_num_1);
        homePosition1 = (AutoCompleteTextView) findViewById(R.id.home_pos_1);
        homePlayer2 = (AutoCompleteTextView) findViewById(R.id.home_name_2);
        homeNumber2 = (TextView) findViewById(R.id.home_num_2);
        homePosition2 = (AutoCompleteTextView) findViewById(R.id.home_pos_2);
        homePlayer3 = (AutoCompleteTextView) findViewById(R.id.home_name_3);
        homeNumber3 = (TextView) findViewById(R.id.home_num_3);
        homePosition3 = (AutoCompleteTextView) findViewById(R.id.home_pos_3);
        homePlayer4 = (AutoCompleteTextView) findViewById(R.id.home_name_4);
        homeNumber4 = (TextView) findViewById(R.id.home_num_4);
        homePosition4 = (AutoCompleteTextView) findViewById(R.id.home_pos_4);
        homePlayer5 = (AutoCompleteTextView) findViewById(R.id.home_name_5);
        homeNumber5 = (TextView) findViewById(R.id.home_num_5);
        homePosition5 = (AutoCompleteTextView) findViewById(R.id.home_pos_5);
        homePlayer6 = (AutoCompleteTextView) findViewById(R.id.home_name_6);
        homeNumber6 = (TextView) findViewById(R.id.home_num_6);
        homePosition6 = (AutoCompleteTextView) findViewById(R.id.home_pos_6);
        homePlayer7 = (AutoCompleteTextView) findViewById(R.id.home_name_7);
        homeNumber7 = (TextView) findViewById(R.id.home_num_7);
        homePosition7 = (AutoCompleteTextView) findViewById(R.id.home_pos_7);
        homePlayer8 = (AutoCompleteTextView) findViewById(R.id.home_name_8);
        homeNumber8 = (TextView) findViewById(R.id.home_num_8);
        homePosition8 = (AutoCompleteTextView) findViewById(R.id.home_pos_8);
        homePlayer9 = (AutoCompleteTextView) findViewById(R.id.home_name_9);
        homeNumber9 = (TextView) findViewById(R.id.home_num_9);
        homePosition9 = (AutoCompleteTextView) findViewById(R.id.home_pos_9);
        homePlayer10 = (AutoCompleteTextView) findViewById(R.id.home_pitcher);
        homeNumber10 = (TextView) findViewById(R.id.home_pitcher_number);

        if (adapter == null) {
            new FindLineups().execute(getIntent().getStringExtra("Home Team").toLowerCase().replace(' ', '-'));
            adapter = new ArrayAdapter<String>(this, R.layout.dropdown_player, arr);
            adapterPos = new ArrayAdapter<String>(this, R.layout.dropdown_player, arr2);
        } else {
            homePlayer1.setText(savedInstanceState1.getString("hPlayer1"));
            homePlayer2.setText(savedInstanceState1.getString("hPlayer2"));
            homePlayer3.setText(savedInstanceState1.getString("hPlayer3"));
            homePlayer4.setText(savedInstanceState1.getString("hPlayer4"));
            homePlayer5.setText(savedInstanceState1.getString("hPlayer5"));
            homePlayer6.setText(savedInstanceState1.getString("hPlayer6"));
            homePlayer7.setText(savedInstanceState1.getString("hPlayer7"));
            homePlayer8.setText(savedInstanceState1.getString("hPlayer8"));
            homePlayer9.setText(savedInstanceState1.getString("hPlayer9"));
            homePlayer10.setText(savedInstanceState1.getString("hPlayer10"));
            homeNumber1.setText(savedInstanceState1.getString("hNum1"));
            homeNumber2.setText(savedInstanceState1.getString("hNum2"));
            homeNumber3.setText(savedInstanceState1.getString("hNum3"));
            homeNumber4.setText(savedInstanceState1.getString("hNum4"));
            homeNumber5.setText(savedInstanceState1.getString("hNum5"));
            homeNumber6.setText(savedInstanceState1.getString("hNum6"));
            homeNumber7.setText(savedInstanceState1.getString("hNum7"));
            homeNumber8.setText(savedInstanceState1.getString("hNum8"));
            homeNumber9.setText(savedInstanceState1.getString("hNum9"));
            homeNumber10.setText(savedInstanceState1.getString("hNum10"));
            homePosition1.setText(savedInstanceState1.getString("hPosition1"));
            homePosition2.setText(savedInstanceState1.getString("hPosition2"));
            homePosition3.setText(savedInstanceState1.getString("hPosition3"));
            homePosition4.setText(savedInstanceState1.getString("hPosition4"));
            homePosition5.setText(savedInstanceState1.getString("hPosition5"));
            homePosition6.setText(savedInstanceState1.getString("hPosition6"));
            homePosition7.setText(savedInstanceState1.getString("hPosition7"));
            homePosition8.setText(savedInstanceState1.getString("hPosition8"));
            homePosition9.setText(savedInstanceState1.getString("hPosition9"));
        }

        teamName.setText(getIntent().getStringExtra("Home Team"));
        if (homeAdapters.size() != 0) {
            homeAdapters.clear();
        }
        homeAdapters.add(homePlayer1);
        homeAdapters.add(homePlayer2);
        homeAdapters.add(homePlayer3);
        homeAdapters.add(homePlayer4);
        homeAdapters.add(homePlayer5);
        homeAdapters.add(homePlayer6);
        homeAdapters.add(homePlayer7);
        homeAdapters.add(homePlayer8);
        homeAdapters.add(homePlayer9);
        homeAdapters.add(homePlayer10);
        for (final AutoCompleteTextView p : homeAdapters) {
            p.setThreshold(1);
            p.setAdapter(adapter);
            p.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (AutoCompleteTextView q : homeAdapters) {
                        if (q.getText().toString().equals(p.getText().toString()) &&
                                homeAdapters.indexOf(q) != homeAdapters.indexOf(p) &&
                                homeAdapters.indexOf(q) != 9 && homeAdapters.indexOf(p) != 9) {
                            p.setText("");
                            Toast.makeText(SetLineup2.this, "You cannot enter the same player twice.", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    int index = homeAdapters.indexOf(p);
                    setNumberText(index, homeNamesAndNumbers.get(p.getText().toString()) + "");
                }
            });
            p.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // on focus off
                        String str = p.getText().toString();
                        int index = homeAdapters.indexOf(p);
                        if (str.equals("")) {
                            setNumberText(index, "");
                            return;
                        }
                        int count = arr.size();
                        for (int i = 0; i < count; i++) {
                            String temp = arr.get(i);
                            if (str.compareTo(temp) == 0) {
                                for (final AutoCompleteTextView q : homeAdapters) {
                                    if (q.getText().toString().equals(p.getText().toString()) &&
                                            homeAdapters.indexOf(q) != homeAdapters.indexOf(p) &&
                                            homeAdapters.indexOf(q) != 9 && homeAdapters.indexOf(p) != 9) {
                                        p.setText("");
                                        setNumberText(index, "");
                                        Toast.makeText(SetLineup2.this, "You cannot enter the same player" +
                                                " twice.", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                                setNumberText(index, homeNamesAndNumbers.get(p.getText().toString())
                                        + "");
                                return;
                            }
                        }
                        p.setText("");
                        setNumberText(index, "");
                        Toast.makeText(SetLineup2.this, "That was not a valid player. Choose from " +
                                "the autocomplete suggestions.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        homePosAdapters.add(homePosition1);
        homePosAdapters.add(homePosition2);
        homePosAdapters.add(homePosition3);
        homePosAdapters.add(homePosition4);
        homePosAdapters.add(homePosition5);
        homePosAdapters.add(homePosition6);
        homePosAdapters.add(homePosition7);
        homePosAdapters.add(homePosition8);
        homePosAdapters.add(homePosition9);
        for (final AutoCompleteTextView p : homePosAdapters) {
            p.setThreshold(0);
            p.setAdapter(adapterPos);
            p.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (AutoCompleteTextView q : homePosAdapters) {
                        if (q.getText().toString().equals(p.getText().toString()) &&
                                homePosAdapters.indexOf(q) != homePosAdapters.indexOf(p)) {
                            p.setText("");
                            Toast.makeText(SetLineup2.this, "You cannot enter the same position twice.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (q.getText().toString().equals("DH") && p.getText().toString().equals("P")
                                || p.getText().toString().equals("DH") && q.getText().toString().equals("P")) {
                            p.setText("");
                            Toast.makeText(SetLineup2.this, "You cannot have a DH and a P.",
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
                                for (final AutoCompleteTextView q : homePosAdapters) {
                                    if (q.getText().toString().equals(p.getText().toString()) &&
                                            homePosAdapters.indexOf(q) != homePosAdapters.indexOf(p)) {
                                        p.setText("");
                                        Toast.makeText(SetLineup2.this, "You cannot enter the same " +
                                                "position twice.", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                                return;
                            }
                        }
                        p.setText("");
                        Toast.makeText(SetLineup2.this, "That was not a valid position. Choose " +
                                "from the autocomplete suggestions.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        nextTeam = (Button) findViewById(R.id.submit_teams);
        nextTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = checkAll();
                if (check == 0) {
                    Toast.makeText(SetLineup2.this, "Congrats! You filled out the lineups.", Toast.LENGTH_LONG).show();
                } else if (check < 3) {
                    Toast.makeText(SetLineup2.this, "Please fill out all information.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SetLineup2.this, "The pitcher in the lineup must be the same as " +
                            "the one at the bottom.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int checkAll() {
        for (final AutoCompleteTextView p : homeAdapters) {
            if (p.getText().toString().equals("")) {
                return 1;
            }
        }
        for (final AutoCompleteTextView p : homePosAdapters) {
            if (p.getText().toString().equals("")) {
                return 2;
            } else if (p.getText().toString().equals("P")) {
                int index = homePosAdapters.indexOf(p);
                if (!homeAdapters.get(index).getText().toString().equals(homePlayer10.getText().toString())) {
                    return 3;
                }
            }
        }
        return 0;
    }

    private void setNumberText(int index, String text) {
        if (index == 0) {
            homeNumber1.setText(text + "");
        } else if (index == 1) {
            homeNumber2.setText(text + "");
        } else if (index == 2) {
            homeNumber3.setText(text + "");
        } else if (index == 3) {
            homeNumber4.setText(text + "");
        } else if (index == 4) {
            homeNumber5.setText(text + "");
        } else if (index == 5) {
            homeNumber6.setText(text + "");
        } else if (index == 6) {
            homeNumber7.setText(text + "");
        } else if (index == 7) {
            homeNumber8.setText(text + "");
        } else if (index == 8) {
            homeNumber9.setText(text + "");
        } else {
            homeNumber10.setText(text + "");
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
            List<Player> home = mAdapterHome.getItems();
            for (Player p : home) {
                while (p.getNumber() == -1) {
                    unique = true;
                    int newNum = new Random().nextInt(99) + 1;
                    for (Player q : home) {
                        if (newNum == q.getNumber()) {
                            unique = false;
                        }
                    }
                    if (unique) {
                        p.setNumber(newNum);
                    }
                }
            }
            mAdapterHome.notifyDataSetChanged();

            homeList = mAdapterHome.getItems();
            for (Player p : homeList) {
                homeNamesAndNumbers.put(p.getFirstName() + " " + p.getLastName(), p.getNumber());
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
                while (result.contains("last_name")) {
                    result = result.substring(result.indexOf("last_name") + 12);
                    String last = result.substring(0, result.indexOf('\"'));
                    String temp = result.substring(result.indexOf("display_name") + 15, result.indexOf("birthdate") - 3);
                    String first = temp.substring(0, temp.length() - last.length() - 1);
                    int num = Integer.valueOf(result.substring(result.indexOf("uniform_number") + 16,
                            result.indexOf(',', result.indexOf("uniform_number") + 16)));
                    Player p = new Player(first, last, num);
                    mAdapterHome.add(p);
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState1 = new Bundle();
        savedInstanceState1.putString("hPlayer1", homePlayer1.getText().toString());
        Log.d("Player 1", savedInstanceState1.getString("hPlayer1"));
        savedInstanceState1.putString("hPlayer2", homePlayer2.getText().toString());
        savedInstanceState1.putString("hPlayer3", homePlayer3.getText().toString());
        savedInstanceState1.putString("hPlayer4", homePlayer4.getText().toString());
        savedInstanceState1.putString("hPlayer5", homePlayer5.getText().toString());
        savedInstanceState1.putString("hPlayer6", homePlayer6.getText().toString());
        savedInstanceState1.putString("hPlayer7", homePlayer7.getText().toString());
        savedInstanceState1.putString("hPlayer8", homePlayer8.getText().toString());
        savedInstanceState1.putString("hPlayer9", homePlayer9.getText().toString());
        savedInstanceState1.putString("hPlayer10", homePlayer10.getText().toString());
        savedInstanceState1.putString("hNum1", homeNumber1.getText().toString());
        savedInstanceState1.putString("hNum2", homeNumber2.getText().toString());
        savedInstanceState1.putString("hNum3", homeNumber3.getText().toString());
        savedInstanceState1.putString("hNum4", homeNumber4.getText().toString());
        savedInstanceState1.putString("hNum5", homeNumber5.getText().toString());
        savedInstanceState1.putString("hNum6", homeNumber6.getText().toString());
        savedInstanceState1.putString("hNum7", homeNumber7.getText().toString());
        savedInstanceState1.putString("hNum8", homeNumber8.getText().toString());
        savedInstanceState1.putString("hNum9", homeNumber9.getText().toString());
        savedInstanceState1.putString("hNum10", homeNumber10.getText().toString());

        super.onSaveInstanceState(savedInstanceState1);
    }

    @Override
    public void onBackPressed() {
        onSaveInstanceState(savedInstanceState1);
        finish();
    }
}
