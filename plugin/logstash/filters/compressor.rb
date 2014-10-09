
require 'java'
require "logstash/filters/base"
require "logstash/namespace"

require File.expand_path(File.dirname(__FILE__))<<"/lib/logstash plugin.jar"
require File.expand_path(File.dirname(__FILE__))<<"/lib/gzipPlugin.jar"
require File.expand_path(File.dirname(__FILE__))<<"/lib/SnappyAlgoritm.jar"

module Sample
	include_package "lz4"
	include_package "gzipplugin"
	include_package "snappyalgoritm"
end

class LogStash::Filters::Compressor < LogStash::Filters::Base

	# Setting the config_name here is required. This is how you
	# configure this filter from your logstash config.

	config_name "compressor"

	# New plugins should start life at milestone 1.
	milestone 1
	
	#set the type of compression
	config :type, :validate => :string, :default => "gzip"
	
	#set action compression or decompression
	config :action, :validate => :string  , :default => "compression" 
	
	
	public
	def register
		# nothing to do
	end # def register

	public
	def filter(event)
		# return nothing unless there's an actual filter event
		#return unless filter?(event)
		
		if event["message"]
			if @type =="snappy"
				mysnappy=Sample::SnappyAlgoritm.new
				if @action =="compression"				
					#compressString=mysnappy.compress(event["message")
			    	compressString=mysnappy.compress64(event["message"])
					event["message"]=compressString
					
					
				elsif @action == "decompression"
					#decompressString=mysnappy.decompress(compressString)
					decompressString=mysnappy.decompress64(compressString)
					event["message"]=decompressString		
				end
			elsif @type =="gzip"
				mygzip=Sample::GzipPlugin.new
				if @action == "compression"				
					compressString=mygzip.compress(event["message"])
					event["message"]=compressString
					decompressString=mygzip.decompress(compressString)
					event["decompress"]=decompressString
				elsif @action == "decompression"
					decompressString=mygzip.decompress(compressString)
					event["message"]=decompressString
				end
			end
		
		
		end
		
	# filter_matched should go in the last line of our successful code
	filter_matched(event)
	end # def filter
end # class LogStash::Filters::Foo

