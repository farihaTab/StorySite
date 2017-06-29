begin
  dbms_scheduler.create_job(job_name        => 'populate_recommnedation',
                            job_type        => 'STORED_PROCEDURE',
                            job_action      => 'populateRecommendation',
                            start_date      => sysdate,
                            repeat_interval => 'freq=daily; INTERVAL=2',
                            end_date        => null,
                            enabled         => true,
                            comments        => 'Update users interest in category');
end;

/*BEGIN
  dbms_scheduler.drop_job(job_name => 'populate_recommneded_tag');
END;
/
*/

