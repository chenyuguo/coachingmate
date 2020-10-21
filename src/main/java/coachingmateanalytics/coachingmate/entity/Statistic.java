package coachingmateanalytics.coachingmate.entity;

public class Statistic {
    private String statistic_id;
    private String serial_number;
    private String session_id;
    private String user_id;
    private String sport_type;
    private String start_time;
    private String access_token;
    private Number start_position_lat;
    private Number start_position_long;
    private Number avg_heart_rate;
    private Number max_heart_rate;
    private Number total_elapesd_time;
    private Number total_distance;
    private Number total_cycles;
    private Number avg_stroke_count;
    private Number avg_stroke_distance;
    private Number total_calories;
    private Number avg_speed;
    private Number max_speed;
    private Number avg_power;
    private Number max_power;
    private Number total_ascent;
    private Number total_descent;
    private Number num_laps;
    private Number training_stress_score;
    private Number intensity_factor;
    private Number pool_length;
    private Number threshold_power;
    private Number avg_cadence;
    private Number max_cadence;
    private Number total_work;
    private Number total_fat_calories;
    private Number normalized_power;
    private Number num_active_length;
    private Number sub_sport;
    private Number tick;

    public Statistic(String access_token, String statistic_id, String user_id, String sport_type, String start_time, Number start_position_lat, Number start_position_long, Number total_elapesd_time, Number total_distance, Number total_cycles, Number avg_stroke_count, Number avg_stroke_distance, Number total_calories, Number avg_speed, Number max_speed, Number avg_power, Number max_power, Number total_ascent, Number total_descent, Number num_laps, Number training_stress_score, Number intensity_factor, Number pool_length, Number threshold_power, Number avg_cadence, Number max_cadence, Number total_fat_calories, Number normalized_power, Number num_active_length, Number sub_sport) {
        this.access_token = access_token;
        this.statistic_id = statistic_id;
        this.user_id = user_id;
        this.sport_type = sport_type;
        this.start_time = start_time;
        this.start_position_lat = start_position_lat;
        this.start_position_long = start_position_long;
        this.total_elapesd_time = total_elapesd_time;
        this.total_distance = total_distance;
        this.total_cycles = total_cycles;
        this.avg_stroke_count = avg_stroke_count;
        this.avg_stroke_distance = avg_stroke_distance;
        this.total_calories = total_calories;
        this.avg_speed = avg_speed;
        this.max_speed = max_speed;
        this.avg_power = avg_power;
        this.max_power = max_power;
        this.total_ascent = total_ascent;
        this.total_descent = total_descent;
        this.num_laps = num_laps;
        this.training_stress_score = training_stress_score;
        this.intensity_factor = intensity_factor;
        this.pool_length = pool_length;
        this.threshold_power = threshold_power;
        this.avg_cadence = avg_cadence;
        this.max_cadence = max_cadence;
        this.total_fat_calories = total_fat_calories;
        this.normalized_power = normalized_power;
        this.num_active_length = num_active_length;
        this.sub_sport = sub_sport;
    }

    public String getAccess_token() {return access_token; }

    public void setAccess_token(String access_token) {this.access_token = access_token; }

    public String getStatistic_id() {
        return statistic_id;
    }

    public void setStatistic_id(String statistic_id) {
        this.statistic_id = statistic_id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSport_type() {
        return sport_type;
    }

    public void setSport_type(String sport_type) {
        this.sport_type = sport_type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Number getStart_position_lat() {
        return start_position_lat;
    }

    public void setStart_position_lat(Number start_position_lat) {
        this.start_position_lat = start_position_lat;
    }

    public Number getStart_position_long() {
        return start_position_long;
    }

    public void setStart_position_long(Number start_position_long) {
        this.start_position_long = start_position_long;
    }

    public Number getAvg_heart_rate() {
        return avg_heart_rate;
    }

    public void setAvg_heart_rate(Number avg_heart_rate) {
        this.avg_heart_rate = avg_heart_rate;
    }

    public Number getMax_heart_rate() {
        return max_heart_rate;
    }

    public void setMax_heart_rate(Number max_heart_rate) {
        this.max_heart_rate = max_heart_rate;
    }

    public Number getTotal_elapesd_time() {
        return total_elapesd_time;
    }

    public void setTotal_elapesd_time(Number total_elapesd_time) {
        this.total_elapesd_time = total_elapesd_time;
    }

    public Number getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(Number total_distance) {
        this.total_distance = total_distance;
    }

    public Number getTotal_cycles() {
        return total_cycles;
    }

    public void setTotal_cycles(Number total_cycles) {
        this.total_cycles = total_cycles;
    }

    public Number getAvg_stroke_count() {
        return avg_stroke_count;
    }

    public void setAvg_stroke_count(Number avg_stroke_count) {
        this.avg_stroke_count = avg_stroke_count;
    }

    public Number getAvg_stroke_distance() {
        return avg_stroke_distance;
    }

    public void setAvg_stroke_distance(Number avg_stroke_distance) {
        this.avg_stroke_distance = avg_stroke_distance;
    }

    public Number getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(Number total_calories) {
        this.total_calories = total_calories;
    }

    public Number getAvg_speed() {
        return avg_speed;
    }

    public void setAvg_speed(Number avg_speed) {
        this.avg_speed = avg_speed;
    }

    public Number getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(Number max_speed) {
        this.max_speed = max_speed;
    }

    public Number getAvg_power() {
        return avg_power;
    }

    public void setAvg_power(Number avg_power) {
        this.avg_power = avg_power;
    }

    public Number getMax_power() {
        return max_power;
    }

    public void setMax_power(Number max_power) {
        this.max_power = max_power;
    }

    public Number getTotal_ascent() {
        return total_ascent;
    }

    public void setTotal_ascent(Number total_ascent) {
        this.total_ascent = total_ascent;
    }

    public Number getTotal_descent() {
        return total_descent;
    }

    public void setTotal_descent(Number total_descent) {
        this.total_descent = total_descent;
    }

    public Number getNum_laps() {
        return num_laps;
    }

    public void setNum_laps(Number num_laps) {
        this.num_laps = num_laps;
    }

    public Number getTraining_stress_score() {
        return training_stress_score;
    }

    public void setTraining_stress_score(Number training_stress_score) {
        this.training_stress_score = training_stress_score;
    }

    public Number getIntensity_factor() {
        return intensity_factor;
    }

    public void setIntensity_factor(Number intensity_factor) {
        this.intensity_factor = intensity_factor;
    }

    public Number getPool_length() {
        return pool_length;
    }

    public void setPool_length(Number pool_length) {
        this.pool_length = pool_length;
    }

    public Number getThreshold_power() {
        return threshold_power;
    }

    public void setThreshold_power(Number threshold_power) {
        this.threshold_power = threshold_power;
    }

    public Number getAvg_cadence() {
        return avg_cadence;
    }

    public void setAvg_cadence(Number avg_cadence) {
        this.avg_cadence = avg_cadence;
    }

    public Number getMax_cadence() {
        return max_cadence;
    }

    public void setMax_cadence(Number max_cadence) {
        this.max_cadence = max_cadence;
    }

    public Number getTotal_work() {
        return total_work;
    }

    public void setTotal_work(Number total_work) {
        this.total_work = total_work;
    }

    public Number getTotal_fat_calories() {
        return total_fat_calories;
    }

    public void setTotal_fat_calories(Number total_fat_calories) {
        this.total_fat_calories = total_fat_calories;
    }

    public Number getNormalized_power() {
        return normalized_power;
    }

    public void setNormalized_power(Number normalized_power) {
        this.normalized_power = normalized_power;
    }

    public Number getNum_active_length() {
        return num_active_length;
    }

    public void setNum_active_length(Number num_active_length) {
        this.num_active_length = num_active_length;
    }

    public Number getSub_sport() {
        return sub_sport;
    }

    public void setSub_sport(Number sub_sport) {
        this.sub_sport = sub_sport;
    }

    public Number getTick() {
        return tick;
    }

    public void setTick(Number tick) {
        this.tick = tick;
    }
}