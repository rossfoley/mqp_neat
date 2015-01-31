require 'rubygems'
require 'nokogiri'
require 'csv'

# Ensure that an input and output file are provided
if ARGV.size < 2
  puts 'Usage: ruby run_xml_to_csv.rb input.xml output.csv'
  exit 1
end

# Prepare the output csv file
CSV.open(ARGV[1], 'w') do |csv|
  # Write the column headers
  csv << ['Generation ID', 
          'Maximum Fitness', 
          'Minimum Fitness', 
          'Average Fitness', 
          'Champion Complexity', 
          'Maximum Complexity', 
          'Minimum Complexity',
          'Average Complexity'] 

  # Open the file and parse it with Nokogiri
  run_xml = Nokogiri::XML(File.open(ARGV[0]))

  # Get a list of the generations
  generations = run_xml.css 'generation'

  generations.each do |gen|
    values = []

    # Generation ID
    values << gen.attr('id').to_i

    # Fitness
    values << gen.css('fitness max').children[0].text.to_i
    values << gen.css('fitness min').children[0].text.to_i
    values << gen.css('fitness avg').children[0].text.to_i

    # Complexity
    values << gen.css('complexity champ').children[0].text.to_i
    values << gen.css('complexity max').children[0].text.to_i
    values << gen.css('complexity min').children[0].text.to_i
    values << gen.css('complexity avg').children[0].text.to_i

    # Output the values to the file
    csv << values
  end
end
