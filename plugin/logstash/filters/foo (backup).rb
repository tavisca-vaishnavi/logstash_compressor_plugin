require 'java'
require "logstash/filters/base"
require "logstash/namespace"
require "/home/msumit/code/plugin/logstash/filters/lib/logstash plugin.jar"
require "/home/msumit/code/plugin/logstash/filters/lib/gzipPlugin.jar"
require "/home/msumit/code/plugin/logstash/filters/lib/SnappyAlgoritm.jar"
module Sample
	include_package "lz4"
	include_package "gzipplugin"
	include_package "snappyalgoritm"
end

class LogStash::Filters::Foo < LogStash::Filters::Base

	# Setting the config_name here is required. This is how you
	# configure this filter from your logstash config.

	config_name "foo"

	# New plugins should start life at milestone 1.
	milestone 1

	# Replace the message with this value.
	config :message, :validate => :string 
	
	#config :host, :validate => :string
	#Have food 
	#config :source , :validate => :string
	
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
		event["beforeIf"] = "value1"
		
		if event["message"]
			event["inMsg"]= "inMsg"
			if @type =="snappy"
				mysnappy=Sample::SnappyAlgoritm.new
				if @action =="compression"				
					#compressString=mysnappy.compress(@message)
			    	compressString=mysnappy.compress64(@message)
					event["message"]=compressString
					decompressString=mysnappy.decompress64(compressString)
					event["original"]=decompressString
				elsif @action == "decompression"
					#decompressString=mysnappy.decompress(compressString)
					decompressString=mysnappy.decompress64(compressString)
					event["message"]=decompressString		
				end
			elsif @type =="gzip"
				mygzip=Sample::GzipPlugin.new
				if @action == "compression"				
					compressString=mygzip.compress(@message)
					event["message"]=compressString
					decompressString=mygzip.decompress(compressString)
					event["original"]=decompressString
					event["type"] = @type
				elsif @action == "decompression"
					decompressString=mygzip.decompress(compressString)
					event["message"]=decompressString
				end
			end
		
		
		
		
				#mygzip=Sample::GzipPlugin.new
				#if @action =="compression"				
				#	compressString=mygzip.compress(@message)
				#	event["message"]=compressString
				#elsif @action == "decompression"
				#	decompressString=mygzip.decompress(compressString)
				#	event["message"]=decompressString
				#end		
				#end
				
			
			#decompressString=mysnappy.decompress(compressString)
			#decompressString=mysnappy.decompress64(compressString)
			#event["decompress"]=decompressString
			#event[@source] ="checkSource"
			#@message="sampleCheck"
				
		#	if @action == "compress"
				#event["message"] = @message.upcase	
		#		orig_len =@message.length
		#		myvar=Sample::Plugin_main.new
		#		var=myvar.compressString(@message)
		#	else 
			#myvar=Sample::Plugin_main.new
				#Return value is a string
			#	uncompress = myvar.decompress_rstring(var,orig_len)
			#	event["uncompress"] = uncompress
			
				#event["message"] = @message.upcase
			#end
																#	mygzip=Sample::GzipPlugin.new
																#	compressString=mygzip.compress(@message)
																#	event["compress"]=compressString
			
																#		decompressString=mygzip.decompress(compressString)
																#			event["decompress"]=decompressString
			
			#mysnappy=Sample::SnappyAlgoritm.new
			#compressString=mysnappy.compress(@message)
			#compressString=mysnappy.compress64(@message)
			#event["compress"]=compressString
			
			#decompressString=mysnappy.decompress(compressString)
			#decompressString=mysnappy.decompress64(compressString)
			#event["decompress"]=decompressString
			#event[@source] ="checkSource"
			#@message="sampleCheck"
			
			#event.has_key?("type")		
			#orig_len =@message.length
			
			#myvar=Sample::Plugin_main.new
			#var=myvar.compressString(@message)
			
			
			#event["compress"]=var
			
		#	myvar.compressString(@message)
			
			#Return value is a string
		#	uncompress = myvar.decompress_rstring(var,orig_len)
			
			
		#	event["uncompress"] = uncompress
						
		end
		
	# filter_matched should go in the last line of our successful code
	filter_matched(event)
	end # def filter
end # class LogStash::Filters::Foo

